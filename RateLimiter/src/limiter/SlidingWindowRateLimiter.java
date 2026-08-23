package limiter;

import entity.RateLimitConfig;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SlidingWindowRateLimiter extends RateLimiter{

    private final Map<String, Queue<Long>> requestLog=new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(RateLimitConfig config) {
        super(config);
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed=new AtomicBoolean(false);
        long currentwindow=System.currentTimeMillis()/1000;
        requestLog.compute(userId,(id,log)->{
            if(log==null)log=new ArrayDeque<>();
            while ((!log.isEmpty() && (currentwindow-log.peek())>config.getWindowInSeconds())){
                log.poll();
            }
            if(log.size()<config.getMaxRequests()){
                allowed.set(true);
                log.add(currentwindow);
            }
            return log;
        });
        return allowed.get();
    }
}
