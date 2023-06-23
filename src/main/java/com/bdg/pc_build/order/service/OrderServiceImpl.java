package com.bdg.pc_build.order.service;

import com.bdg.pc_build.order.repository.OrderDAO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    OrderDAO orderDAO;


}