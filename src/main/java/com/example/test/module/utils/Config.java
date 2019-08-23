package com.example.test.module.utils;

import com.sun.xml.internal.bind.annotation.XmlLocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = "classpath:mapping2.xml")
public class Config {
}
