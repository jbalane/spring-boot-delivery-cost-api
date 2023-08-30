package com.exam.mynt;

import com.exam.mynt.entity.Voucher;
import com.exam.mynt.error.NotFoundException;
import com.exam.mynt.entity.Rule;
import com.exam.mynt.repository.RuleRepository;
import com.exam.mynt.repository.VoucherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@RestController
public class Controller {

    Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired
    RuleRepository ruleRepository;
    @Autowired
    VoucherRepository voucherRepository;

    @PostMapping("/compute")
    public Response compute(@RequestBody Request request) {

        return ((Function<Request, Response>) r -> {
            Double discount = 0D;
            Double volume = r.getLength() * r.getWidth() * r.getHeight();
            List<Rule> rules = ruleRepository.findAll();
            Long count = rules.stream().count();

            //voucher api is not available. loaded vouchers in the db instead
            if(null != request.getVoucher()) {
                Voucher voucher = voucherRepository.findByName(request.getVoucher()).orElse(new Voucher());
                discount = LocalDate.now().isBefore(voucher.getExpiry()) ? voucher.getDiscount() : 0D;
            }

            Rule wRule = rules.stream().filter(rule -> null != rule.getMaxWeight() && r.getWeight() > rule.getMaxWeight())
                    .findFirst()
                    .orElse(null);

            if(null != wRule){
                return new Response(wRule.getName(), wRule.getDescription(), volume, wRule.getCost() * r.getWeight(), discount, (wRule.getCost() * r.getWeight()) - discount);
            }

            Rule vRule = rules.stream().filter(rule -> null != rule.getMaxVolume() && volume < rule.getMaxVolume())
                    .findFirst()
                    .orElse(rules.stream().skip(count -1).findFirst().get());
            return new Response(vRule.getName(), vRule.getDescription(), volume, vRule.getCost() * volume, discount, (vRule.getCost() * volume) - discount);

        }).apply(request);
    }

    @GetMapping("/rules")
    public List<Rule> getAllRules(){
        return ruleRepository.findAll();
    }

    @GetMapping("/vouchers")
    public List<Voucher> getAllVouchers(){
        return voucherRepository.findAll();
    }
    
    @PutMapping("/rules/{id}")
    public Rule updatePrice(@RequestBody Rule newRule, @PathVariable Long id) {
        return ruleRepository.findById(id)
                .map(rule -> {
                    rule.setCost(newRule.getCost());
                    return ruleRepository.save(rule);
                }).orElseThrow(() -> new NotFoundException(id));
    }
}
