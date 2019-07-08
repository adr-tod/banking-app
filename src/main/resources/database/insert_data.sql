-- users

INSERT INTO `users` VALUES
('1', '7477 Sipes Stream Suite 714\nRileymouth, PA 94788-9629', 'ondricka.madisyn@example.com', 'Dr. Kamryn Koch', '312c824912ff7d0a3c0c26b0307d0776b645e2e7', 'igibson', '1'),
('2', '4110 Rutherford Cove Suite 974\nWest Humbertobury, OR 23274-7560', 'orn.chasity@example.com', 'Petra Pouros PhD', '2761ea2532965455985f843e05412b6561ef83da', 'champlin.katarina', '2'),
('3', '5523 Renner Springs Suite 455\nPort Juwanfurt, WI 40434-7840', 'balistreri.zora@example.net', 'Edyth Murray PhD', '6e516ce3305b915d55c83ca76cca2cbd54aa29ab','marcos.bogan', '2'),
('4', '575 Heller Tunnel Suite 416\nCleorafurt, KS 45588-5657', 'hlabadie@example.org', 'Mackenzie Hermiston','8436206130bd09c8c6aed1b69f24eb43746db241', 'odell.mueller', '2'),
('5', '40561 Jamison Gateway\nJaskolskibury, OR 72660', 'valentina.grimes@example.net', 'Amely Kihn', 'b52b8bb8ce8088873b2204f7a4a31bae7416e5e1', 'ricky.lubowitz', '2'),
('6', '0265 Brian Run Apt. 394\nDeltaville, FL 55243-9429', 'berenice.kuhlman@example.net', 'Rowland Bahringer','a35425d1434c0bbff8822c23ea3afc6bcaaf5208','skuhn', '1'),
('7', '28942 Laurianne Neck\nEast Mablemouth, WA 22260', 'jbechtelar@example.net', 'Mrs. Esperanza Pacocha', '022bafec1a0f57506c4becc07be9c8e83f6f6d2e', 'cummings.kali', '2'),
('8', '8161 Larissa Extension Apt. 372\nLake Orlo, AZ 53552-0237', 'ymetz@example.net', 'Napoleon Marks', '4dbc722c055884221037ddf21d88bddb0dc5cbfe', 'eric05', '2'),
('9', '59392 Bailey Hill\nNew Skye, LA 35004-9339', 'franecki.genesis@example.com', 'Adrian Yundt DVM', '752510a1b9027e441dd048419fdbcb1dfdb36617', 'vaughn95', '2'),
('10' ,'9611 Ursula Lane Apt. 296\nChristiansenborough, WA 74486', 'lilliana45@example.com', 'Dr. Rogelio Lebsack IV', '3cf09bd529277e0cdb18cc35cf197113a9f32502', 'sean.feeney', '1');

-- profiles

INSERT INTO `profiles` VALUES
('1', 'admin'),
('2', 'customer');

-- accounts

INSERT INTO `accounts` VALUES
<<<<<<< HEAD
('1', '4110 Rutherford Cove Suite 974\nWest Humbertobury, OR 23274-7560', 'GB17BARC20040164822651', 'Account1', '1', '2'),
('2', '575 Heller Tunnel Suite 416\nCleorafurt, KS 45588-5657', 'GB79BARC20038035182364', 'Account2', '3', '4'),
('3', '0265 Brian Run Apt. 394\nDeltaville, FL 55243-9429', 'GB45BARC20037867547188', 'Account3', '3', '8'),
('4', '8161 Larissa Extension Apt. 372\nLake Orlo, AZ 53552-0237', 'GB81BARC20038076182385', 'Account4', '1', '9'),
('5', '9611 Ursula Lane Apt. 296\nChristiansenborough, WA 74486', 'GB86BARC20037874187555', 'Account5', '4', '7'),
('6', '7477 Sipes Stream Suite 714\nRileymouth, PA 94788-9629', 'GB49BARC20039554574317', 'Account6', '2', '5'),
('7', '5523 Renner Springs Suite 455\nPort Juwanfurt, WI 40434-7840', 'GB50BARC20031865933319', 'Account7', '5', '3');
=======
('1', '4110 Rutherford Cove Suite 974\nWest Humbertobury, OR 23274-7560', 'GB17BARC20040164822651', 'Account1', '1'),
('2', '575 Heller Tunnel Suite 416\nCleorafurt, KS 45588-5657', 'GB79BARC20038035182364', 'Account2', '3'),
('3', '0265 Brian Run Apt. 394\nDeltaville, FL 55243-9429', 'GB45BARC20037867547188', 'Account3', '3'),
('4', '8161 Larissa Extension Apt. 372\nLake Orlo, AZ 53552-0237', 'GB81BARC20038076182385', 'Account4', '1'),
('5', '9611 Ursula Lane Apt. 296\nChristiansenborough, WA 74486', 'GB86BARC20037874187555', 'Account5', '4'),
('6', '7477 Sipes Stream Suite 714\nRileymouth, PA 94788-9629', 'GB49BARC20039554574317', 'Account6', '2'),
('7', '5523 Renner Springs Suite 455\nPort Juwanfurt, WI 40434-7840', 'GB50BARC20031865933319', 'Account7', '5');
>>>>>>> master

-- account statuses

INSERT INTO `accountstatuses` VALUES
('1', 'ACTIVE'),
('2', 'BLOCKED'),
('3', 'BLOCKED_DEBIT'),
('4', 'BLOCKED_CREDIT'),
('5', 'CLOSED');
