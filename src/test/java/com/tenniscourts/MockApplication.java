package com.tenniscourts;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@Profile("test")
@ComponentScan("com.tenniscourts")
public class MockApplication {


}
