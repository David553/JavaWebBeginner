SELECT 1;

INSERT INTO `user` (`id`, `age`, `gender`, `name`, `isDeleted`, `createdTime`, `updatedTime`)
VALUES
(1, 20, 2, '叶落知秋', 0, 1559196465235, 1559196465235),
(2, 22, 1, '零星的话', 0, 1559196465235, 1559196465235);

INSERT INTO `authorities` (`username`,`authority`)
VALUES
('叶落知秋','TALLYER'),
('零星的话','TALLYER');

INSERT INTO `security` (`id`, `username`, `password`, `enable`)
VALUES
(1,'叶落知秋', 123, 1 ),
(2,'零星的话', 123, 1 );