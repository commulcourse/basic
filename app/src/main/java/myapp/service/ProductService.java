package myapp.service;

import java.sql.Connection;
import java.sql.SQLException;

import myapp.model.ProductRepository;

public class ProductService {

        private Connection conn;
        private ProductRepository productRepository;

        public ProductService(ProductRepository productRepository, Connection conn) {
                this.productRepository = productRepository;
                this.conn = conn;
        }

        public void 상품삭제(int id) {
                try {
                        // 트랜잭션 시작
                        productRepository.deleteById(id);
                        // 트랜잭션 종료
                        conn.commit();

                } catch (Exception e) {
                        e.printStackTrace();
                        try {
                                conn.rollback();
                        } catch (Exception e2) {
                                e2.printStackTrace();
                        }
                }
        }

        public void 상품수정(int id, String name, int price, int qty) {
                try {
                        // 트랜잭션 시작
                        productRepository.updateById(id, name, price, qty);
                        // 트랜잭션 종료
                        conn.commit();

                } catch (Exception e) {
                        e.printStackTrace();
                        try {
                                conn.rollback();
                        } catch (Exception e2) {
                                e2.printStackTrace();
                        }
                }

        }

        public void 상품등록(String name, int price, int qty) {
                // 이것은........ @Transactional의 감사함을 배우는 공간이다. 아멘.할렐루야.난 무교.
                try {
                        // 트랜잭션 시작
                        productRepository.insert(name, price, qty);
                        productRepository.insert(name, price, qty);
                        // 트랜잭션 종료
                        conn.commit();

                } catch (Exception e) {
                        e.printStackTrace();
                        try {
                                conn.rollback();
                        } catch (Exception e2) {
                                e2.printStackTrace();
                        }
                }
        }
}