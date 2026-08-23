package factory;

import entity.RateLimitConfig;
import enums.RateLimiType;
import limiter.RateLimiter;
import limiter.*;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimiType algo, RateLimitConfig config) {
        return switch (algo) {
            case TOKEN_BUCKET -> new TokenBucketRateLimiter(config);
            case FIXED_WINDOW -> new FixedWindowRateLimiter(config);
            case SLIDING_WINDOW_LOG -> new SlidingWindowRateLimiter(config);
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algo);
        };
    }
}
