USE egs_bank;

INSERT INTO `customer` (`id`, `first_name`, `last_name`, `national_id`, `passport_number`)
VALUES (1, 'Abolfazl', 'Ansari', '0071234567', '12345678'),
       (2, 'user1', 'user1', '1234567890', NULL),
       (3, 'user2', 'user2', '9876543210', NULL);



INSERT INTO `account` (`id`, `account_number`, `account_status`, `balance`, `creation_time`, `currency`, `customer_id`)
VALUES (100, '123-8000-10171217-001', 'Valid', 1000000, '2021-12-23 15:00:00.000000', 'IRR', 1),
       (101, '123-8000-10171217-002', 'Valid', 5000000, '2021-12-23 16:00:00.000000', 'IRR', 1),
       (102, '123-8000-12345678-001', 'Valid', 1000, '2021-12-23 17:00:00.000000', 'USD', 2);


INSERT INTO `card` (`id`, `auth_count`, `card_number`, `card_status`, `cvv2`, `expiration_date`, `finger_print_hash`,
                    `pin_code_hash`, `account_id`)
VALUES (1000, 0, '5022-1234-8520-5678', 'Valid', 123, '2025-01-01', NULL, NULL, 100),
       (1001, 1, '5022-2910-1234-5678', 'Valid', 1, '2025-02-02', NULL, NULL, 102);
