-- users

INSERT INTO `users` VALUES
('1', '7477 Sipes Stream Suite 714\nRileymouth, PA 94788-9629', 'ondricka.madisyn@example.com', 'Dr. Kamryn Koch', '$2a$10$3fFEOqGr8/bw9ibOJLGEm.hX/IBxAcTFaZd7S/iSROJc1B7g.bUlK', 'igibson', '1'),
('2', '4110 Rutherford Cove Suite 974\nWest Humbertobury, OR 23274-7560', 'orn.chasity@example.com', 'Petra Pouros PhD', '$2a$10$raqrJGry4wnjKla2zTlaIefW5dTs3Tux645BxVXkCmgZ3ByDgXFzK', 'champlin.katarina', '2'),
('3', '5523 Renner Springs Suite 455\nPort Juwanfurt, WI 40434-7840', 'balistreri.zora@example.net', 'Edyth Murray PhD', '$2a$10$4waT.pQXP7sLdk5TCRoIhe0J/feWsjeKuj0fYqpbmRe6GIp/BWTsO','marcos.bogan', '2'),
('4', '575 Heller Tunnel Suite 416\nCleorafurt, KS 45588-5657', 'hlabadie@example.org', 'Mackenzie Hermiston','$2a$10$eXkuvG1gVZZpQKxFfmueS.wE51oQWdJ7OHjBECgz018CQSeb0UIE.', 'odell.mueller', '2'),
('5', '40561 Jamison Gateway\nJaskolskibury, OR 72660', 'valentina.grimes@example.net', 'Amely Kihn', '$2a$10$xJeCLZa21L0NYGg7h9/8fO2oKdJCDD/eYsaHLk3myPu5HPJzAlFB6', 'ricky.lubowitz', '2'),
('6', '0265 Brian Run Apt. 394\nDeltaville, FL 55243-9429', 'berenice.kuhlman@example.net', 'Rowland Bahringer','$2a$10$VIHUuwpZLvuidRuZpkC4he4kKvMx5djujzOeFRq6X2PQ59fF5hOdy','skuhn', '1'),
('7', '28942 Laurianne Neck\nEast Mablemouth, WA 22260', 'jbechtelar@example.net', 'Mrs. Esperanza Pacocha', '$2a$10$/c/WtRhx20RyW8LMlNfofesMMUYpNXldX3T4DvmijRjJdLPTUygOi', 'cummings.kali', '2'),
('8', '8161 Larissa Extension Apt. 372\nLake Orlo, AZ 53552-0237', 'ymetz@example.net', 'Napoleon Marks', '$2a$10$DbJInkcE4YpLtiqimIQ1PeW4Bo7TIsrFD8YQ4CPMNUX825TIMjYzm', 'eric05', '2'),
('9', '59392 Bailey Hill\nNew Skye, LA 35004-9339', 'franecki.genesis@example.com', 'Adrian Yundt DVM', '$2a$10$QZ4e.4fFWR61ucKBi98QTORrQYzD96w3fpJhCvTS9lqTwHMqv7zqm', 'vaughn95', '2'),
('10' ,'9611 Ursula Lane Apt. 296\nChristiansenborough, WA 74486', 'lilliana45@example.com', 'Dr. Rogelio Lebsack IV', '$2a$10$7xVp39JQLcw.9KYLMcUgdO5PMRMEzGMsFB1VcyPhKAH9TTGpTOadi', 'sean.feeney', '1');

-- profiles

INSERT INTO `profiles` VALUES
('1', 'admin'),
('2', 'customer');

-- accounts

INSERT INTO `accounts` VALUES
('1', '4110 Rutherford Cove Suite 974\nWest Humbertobury, OR 23274-7560', 'GB17BARC20040164822651', 'Account1', '7', '2', '1', '2'),
('2', '575 Heller Tunnel Suite 416\nCleorafurt, KS 45588-5657', 'GB79BARC20038035182364', 'Account2', '6', '1', '3', '4'),
('3', '0265 Brian Run Apt. 394\nDeltaville, FL 55243-9429', 'GB45BARC20037867547188', 'Account3', '5', '2', '3', '8'),
('4', '8161 Larissa Extension Apt. 372\nLake Orlo, AZ 53552-0237', 'GB81BARC20038076182385', 'Account4', '4', '3', '4', '9'),
('5', '9611 Ursula Lane Apt. 296\nChristiansenborough, WA 74486', 'GB86BARC20037874187555', 'Account5', '3', '4', '2', '7'),
('6', '7477 Sipes Stream Suite 714\nRileymouth, PA 94788-9629', 'GB49BARC20039554574317', 'Account6', '2', '5', '1', '5'),
('7', '5523 Renner Springs Suite 455\nPort Juwanfurt, WI 40434-7840', 'GB50BARC20031865933319', 'Account7', '1', '1', '5', '3');

-- account statuses

INSERT INTO `accountstatuses` VALUES
('1', 'ACTIVE'),
('2', 'BLOCKED'),
('3', 'BLOCKED_DEBIT'),
('4', 'BLOCKED_CREDIT'),
('5', 'CLOSED');

-- balances

INSERT INTO `balances` VALUES
('1', '2000'),
('2', '15500'),
('3', '5750'),
('4', '900'),
('5', '1355'),
('6', '260'),
('7', '175');

-- currencies

INSERT INTO `currencies` VALUES
('1', 'USD'),
('2', 'EUR'),
('3', 'JPY'),
('4', 'GBP'),
('5', 'MXN');

-- payment statuses

INSERT INTO `paymentstatuses` VALUES
('1', 'VERIFY'),
('2', 'APPROVE'),
('3', 'AUTHORIZE'),
('4', 'COMPLETED'),
('5', 'CANCELLED');

-- payments

INSERT INTO `payments` VALUES
('1', '250', '2016-06-12 21:34:14', '3', '2', '5', '5'),
('2', '100', '2016-06-13 08:31:39', '5', '1', '4', '4'),
('3', '660', '2016-06-16 10:55:05', '4', '4', '6', '3'),
('4', '300', '2016-06-18 09:05:11', '6', '2', '5', '2'),
('5', '55', '2016-06-18 15:18:46', '5', '1', '7', '2'),
('6', '50', '2016-06-19 19:58:23', '7', '4', '3', '1');