package com.pasindu15.demo.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class LibraryApplication {
	public static void main(String[] args) {
		try {
			setHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		SpringApplication.run(LibraryApplication.class, args);
	}
	private static void setHostAddress() throws UnknownHostException {
		InetAddress ip = InetAddress.getLocalHost();
		String hostAddress = ip.getHostAddress();
		System.setProperty("host.address",hostAddress);
	}

}
