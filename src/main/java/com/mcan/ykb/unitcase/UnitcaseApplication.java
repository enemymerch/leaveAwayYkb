package com.mcan.ykb.unitcase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import java.awt.print.Book;
import java.util.Arrays;

@SpringBootApplication
public class UnitcaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnitcaseApplication.class);
	}
}

