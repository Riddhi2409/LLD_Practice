package limiter;

import entity.RateLimitConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedWindowRateLimiter extends RateLimiter{

    Map<String,Integer> requestCount=new ConcurrentHashMap<>();//userId->countOfReguestMade
    Map<String,Long> windowStart=new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(RateLimitConfig config){
        super(config);
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentWindow=System.currentTimeMillis()/1000/config.getWindowInSeconds();
       AtomicBoolean allowed=new AtomicBoolean(false);
        requestCount.compute(userId,(id,count)->{
            long lastWindow=windowStart.getOrDefault(userId,currentWindow);
            if(lastWindow!=currentWindow){
                windowStart.put(userId,currentWindow);
                allowed.set(true);
                return 1;
            }
            if(count==null) count=0;
            if(count< config.getMaxRequests()){
                allowed.set(true);
                return count+1;
            }
            return count;
        });

        return allowed.get();

    }
}
