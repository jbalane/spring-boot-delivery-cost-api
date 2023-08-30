package com.exam.mynt;

import com.exam.mynt.entity.Rule;
import com.exam.mynt.entity.Voucher;
import com.exam.mynt.repository.RuleRepository;
import com.exam.mynt.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	RuleRepository ruleRepository;
	@Autowired
	VoucherRepository voucherRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//load rule default values
		ruleRepository.save(new Rule(null, "REJECT", "Weight exceeds 50kg", 50D, null, 0D));
		ruleRepository.save(new Rule(null, "HEAVY", "Weight exceeds 10kg", 10D, null, 20D));
		ruleRepository.save(new Rule(null, "SMALL", "Volume is less than 1500cm\u00B3", null, 1500D, 0.03D));
		ruleRepository.save(new Rule(null, "MEDIUM", "Volume is less than 2500cm\u00B3", null, 2500D, 0.04D));
		ruleRepository.save(new Rule(null, "LARGE", "", null, null, 0.05D));

		//load voucher default values
		voucherRepository.save(new Voucher(null, "MYNT", 10D, LocalDate.parse("2024-08-30")));
		voucherRepository.save(new Voucher(null, "GFI", 5D, LocalDate.parse("2022-08-30")));
		voucherRepository.save(new Voucher(null, "skdlks", 3D, LocalDate.parse("2024-08-30")));

	}

}
