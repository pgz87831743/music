package jx.pgz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
public class WorkOrderStarter {

    public static void main(String[] args) {
        SpringApplication.run(WorkOrderStarter.class);
    }
}
