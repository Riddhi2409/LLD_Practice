package limiter;

import entity.RateLimitConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketRateLimiter extends RateLimiter{

    private final Map<String,Integer> tokens=new ConcurrentHashMap<>();
    private final Map<String,Long> lastRefillTime=new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(RateLimitConfig config) {
        super(config);
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed=new AtomicBoolean(false);
        long now=System.currentTimeMillis();
        tokens.compute(userId,(id,availableTokens)->{
            int currentTokens=refillTokens(id,now);
            if(currentTokens>0){
                allowed.set(true);
                return currentTokens-1;
            }
            return currentTokens;
        });

        return allowed.get();

    }

    private int refillTokens(String userId,long now){
        double refilRate=(double) config.getMaxRequests()/config.getWindowInSeconds();

        lastRefillTime.putIfAbsent(userId,now);
        long lastRefill=lastRefillTime.get(userId);
        long elapsedSeconds = (now - lastRefill) / 1000;
        int refillTokens= (int) ((int) elapsedSeconds*refilRate);
        int currentTokens = tokens.getOrDefault(userId, config.getMaxRequests());
        currentTokens = Math.min(config.getMaxRequests(), currentTokens + refillTokens);

        if (refillTokens > 0) lastRefillTime.put(userId, now);
        return currentTokens;

    }
}
