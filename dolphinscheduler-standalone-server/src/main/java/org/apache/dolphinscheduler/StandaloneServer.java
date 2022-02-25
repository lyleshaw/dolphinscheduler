/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler;

import org.apache.curator.test.TestingServer;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StandaloneServer {

    @Value("${spring.jackson.time-zone:UTC}")
    private String timezone;

    public static void main(String[] args) throws Exception {
        final TestingServer server = new TestingServer(true);
        System.setProperty("registry.zookeeper.connect-string", server.getConnectString());
        SpringApplication.run(StandaloneServer.class, args);
    }

    @PostConstruct
    public void run() {
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
    }
}