package vn.toancauxanh.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import vn.toancauxanh.model.VanBan;

public interface VanBanRepository extends JpaRepository<VanBan, Long>, QueryDslPredicateExecutor<VanBan>{
	
}
