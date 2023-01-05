package com.jjj.cashlesstips.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "application.order.pagination")
@NoArgsConstructor
@Getter
@Setter
public class OrderConfiguration {
    
    private int defaultPageNumber;
    
    private int defaultPageSize;
    
    private String defaultSortBy;
    
    private String defaultSortOrder;
}
