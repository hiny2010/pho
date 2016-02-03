package com.eharmony.services.mymatchesservice.service.merger;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.eharmony.services.mymatchesservice.rest.MatchFeedRequestContext;
@Component
public class FeedMergeStrategyManager {

	private FeedMergeStrategy VOLDY_WITH_PROFILE_MERGE_STRATEGY = new DefaultFeedMergeStrategyImpl();
	
	private FeedMergeStrategy HBASE_FEED_ONLY_STRATEGY = new DefaultFeedMergeStrategyImpl();
	@Resource
	private FeedMergeStrategy HBASE_WITH_REDIS_MERGE_STRATEGY;
    
    public FeedMergeStrategy getMergeStrategy(MatchFeedRequestContext requestContext){
    	
    	switch(requestContext.getFeedMergeType()){
    	
    	case VOLDY_FEED_WITH_PROFILE_MERGE:
    		
    		return VOLDY_WITH_PROFILE_MERGE_STRATEGY;
    	case HBASE_FEED_ONLY:
    		return HBASE_FEED_ONLY_STRATEGY;
    		
    	case VOLDY_FEED_ONLY: 
    	case VOLDY_FEED_WITH_FULL_MERGE:
    		
    		throw new UnsupportedOperationException("Unsupported Merge Strategy: " + requestContext.getFeedMergeType());
    	
		case HBASE_FEED_WITH_MATCH_MERGE:
			return HBASE_WITH_REDIS_MERGE_STRATEGY;
    	}
			
    	return VOLDY_WITH_PROFILE_MERGE_STRATEGY;
    	
    }
}