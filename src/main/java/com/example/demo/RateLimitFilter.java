package com.example.demo;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitFilter extends OncePerRequestFilter 
{

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    private Bucket createBucket() 
    {
        Bandwidth limit = Bandwidth.simple(60, Duration.ofSeconds(1));
        return Bucket.builder().addLimit(limit).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException 
    {
    	
    	//System.out.println("Filter hit");
        String ip = request.getRemoteAddr();
        Bucket bucket = cache.computeIfAbsent(ip, k -> createBucket());

        if (bucket.tryConsume(1)) 
            filterChain.doFilter(request, response);
        else 
        {
            response.setStatus(429);
            response.getWriter().write("Too many requests");
        }
    }
}