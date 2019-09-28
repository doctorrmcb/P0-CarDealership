package com.revature.pojos.finance;

import java.time.LocalDateTime;

import com.revature.pojos.Car;
import com.revature.pojos.user.User;

public class Payment {
	public double amount;
	public User user;
	public LocalDateTime paymentDate;
	public Car car;
}
