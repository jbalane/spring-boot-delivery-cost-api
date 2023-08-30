package com.exam.mynt.repository;

import com.exam.mynt.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    public Optional<Voucher> findByName(String name);
}
