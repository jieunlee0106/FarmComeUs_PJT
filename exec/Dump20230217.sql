-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: i8b103.p.ssafy.io    Database: farmcu
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` bigint NOT NULL AUTO_INCREMENT,
  `cart_item_count` int NOT NULL,
  `item_id` bigint DEFAULT NULL,
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FK9fhia6b3ekuddn6pkxlsks7rr` (`item_id`),
  KEY `FKix170nytunweovf2v9137mx2o` (`member_id`),
  CONSTRAINT `FK9fhia6b3ekuddn6pkxlsks7rr` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  CONSTRAINT `FKix170nytunweovf2v9137mx2o` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,2,9,14);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_detail`
--

DROP TABLE IF EXISTS `category_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_detail` (
  `detail_code` bigint NOT NULL AUTO_INCREMENT,
  `detail_name` varchar(255) DEFAULT NULL,
  `title_code` bigint DEFAULT NULL,
  PRIMARY KEY (`detail_code`),
  KEY `FK67d6o1kmymhbyf8mlcm4c7hix` (`title_code`),
  CONSTRAINT `FK67d6o1kmymhbyf8mlcm4c7hix` FOREIGN KEY (`title_code`) REFERENCES `category_title` (`title_code`)
) ENGINE=InnoDB AUTO_INCREMENT=655 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_detail`
--

LOCK TABLES `category_detail` WRITE;
/*!40000 ALTER TABLE `category_detail` DISABLE KEYS */;
INSERT INTO `category_detail` VALUES (111,'쌀',100),(112,'찹쌀',100),(141,'콩',100),(142,'팥',100),(143,'녹두',100),(144,'메밀',100),(151,'고구마',100),(152,'감자',100),(211,'배추',200),(212,'양배추',200),(213,'시금치',200),(214,'상추',200),(215,'얼갈이배추',200),(216,'갓',200),(221,'수박',200),(222,'참외',200),(223,'오이',200),(224,'호박',200),(225,'토마토',200),(226,'딸기',200),(231,'무',200),(232,'당근',200),(233,'열무',200),(241,'건고추',200),(242,'풋고추',200),(243,'붉은고추',200),(244,'피마늘',200),(245,'양파',200),(246,'파',200),(247,'생강',200),(248,'고춧가루',200),(251,'가지',200),(252,'미나리',200),(253,'깻잎',200),(254,'부추',200),(255,'피망',200),(256,'파프리카',200),(257,'멜론',200),(258,'깐마늘(국산)',200),(259,'깐마늘(수입)',200),(312,'참깨',300),(313,'들깨',300),(314,'땅콩',300),(315,'느타리버섯',300),(316,'팽이버섯',300),(317,'새송이버섯',300),(318,'호두',300),(319,'아몬드',300),(411,'사과',400),(412,'배',400),(413,'복숭아',400),(414,'포도',400),(415,'감귤',400),(416,'단감',400),(418,'바나나',400),(419,'참다래',400),(420,'파인애플',400),(421,'오렌지',400),(422,'방울토마토',200),(423,'자몽',400),(424,'레몬',400),(425,'체리',400),(426,'건포도',400),(427,'건블루베리',400),(428,'망고',400),(512,'쇠고기',500),(514,'돼지고기',500),(515,'닭고기',500),(516,'계란',500),(535,'우유',500),(611,'고등어',600),(612,'꽁치',600),(613,'갈치',600),(615,'명태',600),(619,'물오징어',600),(638,'건멸치',600),(639,'북어',600),(640,'건오징어',600),(641,'김',600),(642,'건미역',600),(644,'굴',600),(649,'수입조기',600),(650,'새우젓',600),(651,'멸치액젓',600),(652,'굵은소금',600),(653,'전복',600),(654,'새우',600);
/*!40000 ALTER TABLE `category_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_title`
--

DROP TABLE IF EXISTS `category_title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_title` (
  `title_code` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  `title_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`title_code`)
) ENGINE=InnoDB AUTO_INCREMENT=601 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_title`
--

LOCK TABLES `category_title` WRITE;
/*!40000 ALTER TABLE `category_title` DISABLE KEYS */;
INSERT INTO `category_title` VALUES (100,'https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/sikrhang.jpg','식량작물'),(200,'https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/vegetable.jpg','채소류'),(300,'https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/teukyong.jpg','특용작물'),(400,'https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/fruit.jpg','과일류'),(500,'https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/chuksan.jpg','축산물'),(600,'https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/susan.jpg','수산물');
/*!40000 ALTER TABLE `category_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_info`
--

DROP TABLE IF EXISTS `delivery_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_info` (
  `delivery_id` bigint NOT NULL AUTO_INCREMENT,
  `delivery_addr` varchar(255) NOT NULL,
  `delivery_method` varchar(255) DEFAULT NULL,
  `delivery_name` varchar(10) NOT NULL,
  `delivery_phone_number` varchar(15) NOT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`delivery_id`),
  KEY `FKmuermp4jp462pdu8uqhpglwpu` (`member_id`),
  CONSTRAINT `FKmuermp4jp462pdu8uqhpglwpu` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_info`
--

LOCK TABLES `delivery_info` WRITE;
/*!40000 ALTER TABLE `delivery_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(26),(26),(26);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `item_id` bigint NOT NULL AUTO_INCREMENT,
  `item_created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `item_description` varchar(255) NOT NULL,
  `item_discount` int DEFAULT NULL,
  `item_name` varchar(15) NOT NULL,
  `item_price` int NOT NULL,
  `item_stock` int DEFAULT NULL,
  `category_detail_code` bigint DEFAULT NULL,
  `category_title_code` bigint DEFAULT NULL,
  `store_id` bigint DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FK966dn63yko60m1mpk8k5nj2s9` (`category_detail_code`),
  KEY `FKbanrd4phsnkotbc5jhvlxskto` (`category_title_code`),
  KEY `FKi0c87m5jy5qxw8orrf2pugs6h` (`store_id`),
  CONSTRAINT `FK966dn63yko60m1mpk8k5nj2s9` FOREIGN KEY (`category_detail_code`) REFERENCES `category_detail` (`detail_code`),
  CONSTRAINT `FKbanrd4phsnkotbc5jhvlxskto` FOREIGN KEY (`category_title_code`) REFERENCES `category_title` (`title_code`),
  CONSTRAINT `FKi0c87m5jy5qxw8orrf2pugs6h` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (9,'2023-02-16 11:58:14','올 가을 수확한 사과입니다. 당도가 아주 높고 아삭한 식감이 일품입니다.',0,'당도높은 아삭 가을 사과',18000,0,411,400,10),(10,'2023-02-16 12:05:14','선물용으로 아주 좋은 수통골 배입니다. 주문 후 연락 주시면 정성스럽게 포장하여 배송해드립니다.',0,'과즙 풍부한 수통골 배',34000,54,412,400,10),(11,'2023-02-16 12:36:19','양평 돼지 농장에서 무항생제로 키운 돼지 통삼겹입니다. 캠핑 갈 때 다함께 즐기기 아주 좋습니다.',0,'무항생제 통삼겹',12000,55,514,500,9),(12,'2023-02-16 12:41:51','담백하고 고소한 무항생제 통목살입니다. 친구들과, 온 가족이 캠핑 가서 먹기 아주 좋습니다.',0,'고소한 통돼지 목살',16000,30,514,500,9),(13,'2023-02-16 12:56:15','돼지껍데기는 냉장 상태일때 변색되기 쉽고 신선도 유지가 어렵기 때문에 저희 농장에서 정성스럽게 손질하여 포장해 배송해드립니다.',0,'콜라겐이 풍부한 돼지껍데기',8000,10,514,500,9),(14,'2023-02-16 16:19:57','씨가 없는 포도로 먹기 편하고 당도가 아주 높아 맛이 좋습니다.',0,'고당도 씨 없는 포도',9000,207,414,400,10),(15,'2023-02-16 19:23:56','달고 맛있는 아침햇살 사과입니다. 농부의 정성을 다해 키웠습니다.',0,'아침햇살 사과 10kg',20000,100,411,400,2),(16,'2023-02-16 23:48:00','특제 소스가 포함된 돼지 갈비로 아이들 밥 반찬으로 좋습니다.',0,'돼지갈비',34000,30,514,500,9);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_image`
--

DROP TABLE IF EXISTS `item_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_image` (
  `item_image_id` bigint NOT NULL AUTO_INCREMENT,
  `original_name` varchar(255) DEFAULT NULL,
  `saved_path` varchar(255) DEFAULT NULL,
  `item_id` bigint DEFAULT NULL,
  PRIMARY KEY (`item_image_id`),
  KEY `FKta6kqet3u8mv95y7jwtgwqpys` (`item_id`),
  CONSTRAINT `FKta6kqet3u8mv95y7jwtgwqpys` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_image`
--

LOCK TABLES `item_image` WRITE;
/*!40000 ALTER TABLE `item_image` DISABLE KEYS */;
INSERT INTO `item_image` VALUES (8,'apple.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/apple.jpg',9),(9,'pear.png','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/pear.png',10),(10,'forkbelly.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/forkbelly.jpg',11),(11,'moksal.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/moksal.jpg',12),(12,'pigkkupdaegi.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/pigkkupdaegi.jpg',13),(13,'grapes.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/grapes.jpg',14),(14,'사과.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/%EC%82%AC%EA%B3%BC.jpg',15),(15,'galbi.jfif','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/galbi.jfif',16);
/*!40000 ALTER TABLE `item_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live`
--

DROP TABLE IF EXISTS `live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live` (
  `live_id` bigint NOT NULL AUTO_INCREMENT,
  `live_discount` int DEFAULT NULL,
  `live_end` datetime(6) DEFAULT NULL,
  `live_start` datetime(6) NOT NULL,
  `live_stock` int DEFAULT NULL,
  `live_title` varchar(255) DEFAULT NULL,
  `live_viewers` int DEFAULT NULL,
  `item_id` bigint DEFAULT NULL,
  `store_id` bigint DEFAULT NULL,
  PRIMARY KEY (`live_id`),
  KEY `FKnpuo7nl50qk03hlg3kuj6ey2l` (`item_id`),
  KEY `FK412ci7qmp5rclbs25sqt9rm4m` (`store_id`),
  CONSTRAINT `FK412ci7qmp5rclbs25sqt9rm4m` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`),
  CONSTRAINT `FKnpuo7nl50qk03hlg3kuj6ey2l` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live`
--

LOCK TABLES `live` WRITE;
/*!40000 ALTER TABLE `live` DISABLE KEYS */;
INSERT INTO `live` VALUES (7,6,'2023-02-19 02:30:00.000000','2023-02-19 01:30:00.000000',0,'[명절 특가] 과즙 풍부한 수통골 배',0,10,10),(9,12,'2023-02-18 08:00:00.000000','2023-02-18 07:00:00.000000',0,'[특가] 캠핑갈때 아주 좋은 통삼겹',0,11,9),(12,8,'2023-02-19 02:00:00.000000','2023-02-19 01:00:00.000000',0,'무항생제로 키운 통목살 특가',0,12,9),(13,6,'2023-02-17 15:30:00.000000','2023-02-17 14:30:00.000000',0,'수통골 가을 햇사과',0,9,10),(14,14,'2023-02-16 16:00:00.000000','2023-02-16 15:00:00.000000',0,'[오늘만 할인] 냉동 돼지 껍데기',0,13,9),(15,0,'2023-02-16 17:30:00.000000','2023-02-16 16:30:00.000000',0,'아이들 간식으로 너무 좋은 씨 없는 포도',0,14,10),(16,12,'2023-02-16 19:30:00.000000','2023-02-16 18:30:00.000000',0,'캠핑가기 좋은 날 먹기 좋은 통삼겹',0,11,9),(17,30,'2023-02-17 05:25:00.000000','2023-02-17 04:25:00.000000',0,'달고 맛있는 아침햇살 사과입니다.',0,15,2);
/*!40000 ALTER TABLE `live` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live_like`
--

DROP TABLE IF EXISTS `live_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live_like` (
  `live_like_id` bigint NOT NULL AUTO_INCREMENT,
  `live_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`live_like_id`),
  KEY `FKdc1w8vbd0q1ql0u03kci6jkqd` (`live_id`),
  KEY `FKhiev6scwy51el5psb6t1u5xuc` (`member_id`),
  CONSTRAINT `FKdc1w8vbd0q1ql0u03kci6jkqd` FOREIGN KEY (`live_id`) REFERENCES `live` (`live_id`),
  CONSTRAINT `FKhiev6scwy51el5psb6t1u5xuc` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live_like`
--

LOCK TABLES `live_like` WRITE;
/*!40000 ALTER TABLE `live_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `live_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `detail_addr` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `profile_img` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `provider_id` varchar(100) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `street_addr` varchar(50) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2023-02-14 08:06:03.198000','삼성화재 연수원','myfarm@ssafy.com','myfarm','팜컴어스','착한 대파','$2a$10$WsuqfpTfasth2eHat5CjZO88jefD1suphMaYUQZBSPqrN5tKR4Jde','01012341234',NULL,NULL,NULL,'ROLE_USER','myfarm@ssafy.com','34153'),(2,'2023-02-14 08:07:15.798000','삼성화재 유성캠퍼스','euntol@naver.com','kty_test','김태영','지친 고구마','$2a$10$o0.OX/Mb8iiQXtJdtZgRNu55/v.fsRcI8yguXcgXoED8dshwEfgKu','01012341234',NULL,NULL,NULL,'ROLE_USER','대전 유성구 동서대로 98-39','34153'),(3,'2023-02-14 17:38:22.727000','102호','chacha@gmail.com','chacha','차현경','보은 달걀','$2a$10$OQDhudj7GFz65xOHITksSO7c17MXZGRAFOJxfXcD8SgYLe3ZUZwYG','01012345678',NULL,NULL,NULL,'ROLE_USER','대전 유성구 동서대로 98-39','34153'),(4,'2023-02-14 17:47:24.997000','삼성화재 유성 연수원','myfarmy@gmail.com','myfarmy','파미팜','보은 사과','$2a$10$FzEjYZrA7.0o7MbLeMKBk.kARB/.LNKf4RbEnqJYUG4DiQUaS9A22','01012341234',NULL,NULL,NULL,'ROLE_USER','대전 유성구 동서대로 98-39','34153'),(5,'2023-02-14 17:50:20.300000','102호','Jihee@ssafy.com','Jihee','지희','착한 쪽파','$2a$10$l5OV9l4Q5UJBcnUqkYfdoOmT6MG0Gz9msyZlItFXXZL4D3ywci/sO','01012341234',NULL,NULL,NULL,'ROLE_USER','대전 유성구 동서대로 98-39','34153'),(6,'2023-02-14 18:11:59.628000','102호','Jisung@ssafy.com','Jisung','박지성','매운 사과','$2a$10$c9MpJYj8asX/TZorhTZ9T.oSL9O9zKbHS4N88.U9rNCgIM7SbPeoa','01012341234',NULL,NULL,NULL,'ROLE_USER','대전 유성구 동서대로 98-39','34153'),(7,'2023-02-14 22:50:52.441000','101-1103','ljs3112@naver.com','ljs3112','이정선','착한 고구마','$2a$10$NgJVMN7.ut7kuP2yoh5UW.zjRZNsWqfHiQ8o63i0d5cn5Tz7.3KtG','01055229797',NULL,NULL,NULL,'ROLE_USER','경기 성남시 분당구 경부고속도로 409','13473'),(9,'2023-02-15 00:33:19.826000','1층','test12211@naver.com','test12211','이성중','새콤 상추','$2a$10$A8cJTSyfRoF3ixwEYqUwTOyF8MeFqePW6DCI3JrXxHn.Szk4EwPva','01055449979',NULL,NULL,NULL,'ROLE_USER','충북 청주시 상당구 가덕면 가덕시동길 27-7','28204'),(11,'2023-02-15 03:34:10.697000','삼성화재 유성 연수원','funfarm@ssafy.com','funfarm','펀앤팜','귀여운 대파','$2a$10$tsvhIUxmTFLTJJNoL9N6kOF85nxvOtaZVH57L7NFuFVZL2DRfcuam','01012341234',NULL,NULL,NULL,'ROLE_USER','funfarm@ssafy.com','34153'),(12,'2023-02-15 06:20:37.656000','210호','eekn224@naver.com','test1166','최성중','부끄러운 양파','$2a$10$6jk4nS3.7E77ZOkTrLEYL./gsLz7c0afVHCKcK/AqA5GupMrrbqu2','01066773159',NULL,NULL,NULL,'ROLE_USER','경기 안성시 죽산면 당진길 5-11','17524'),(13,'2023-02-15 06:29:48.449000','210호','jangjh9911@naver.com','test9911','장재훈','새콤 양파','$2a$10$Q5zfa.PdeudfxTQmTqh54em9Ws5szgl3oJWftDFx0SgaZdB52IA2S','01099119911',NULL,NULL,NULL,'ROLE_USER','충북 청주시 상당구 가덕면 가덕시동길 10','28204'),(14,'2023-02-16 03:25:17.507000','102호','sungsim@gmail.conm','sungsim','성심당','지친 사과','$2a$10$Gdq4mu63OPanOLTc6p4cuewt0m6VkDcdJ7YDtH3c1yIpzWHqrUOK.','01000000000',NULL,NULL,NULL,'ROLE_USER','경기 김포시 북변중로 66-2','10098'),(15,'2023-02-16 07:30:39.193000','삼성화재 연수원','juicyFruit@ssafy.com','juicyFruit','홍사과','새콤 자몽','$2a$10$WZo9r7b3Tx/T5Q0QlMiCeerQtX8FBu1hYYYPZkEthm8S2FNTcQURe','01045679549',NULL,NULL,NULL,'ROLE_USER','대전 유성구 동서대로 98-39','34153'),(16,'2023-02-16 07:47:39.434000','1층','jbk0000@naver.com','sjk0000','정보근','지친 양파','$2a$10$osOuz4CbBc4KJJWWquSDRu0s/dT4VWPnEDSMLbOcOeIJJXhU423uu','01088764790',NULL,NULL,NULL,'ROLE_USER','제주특별자치도 서귀포시 가가로 15','63534'),(17,'2023-02-16 12:09:00.449000','101동 732호','eon9931@naver.com','eon9999','김성중','부끄러운 상추','$2a$10$bTuMvlm17QwsQdnql7Aht.uVxzVzpi.McNDtHwA4ejlkkFzlpciy2','01099319931',NULL,NULL,NULL,'ROLE_USER','대전 유성구 온천북로 26','34186'),(18,'2023-02-16 12:20:27.884000','삼성화재 연수원 101호','smartpodo@kakao.com','smartpodo','김동욱','크롱크롱','$2a$10$hEQ/WSZWJgTuoW0YirwVKOUxuXsJtBDso4D.1JK88WpqaDcgvDNPW','01079977161',NULL,NULL,NULL,'ROLE_USER','대전 유성구 동서대로 98-39','34153'),(20,'2023-02-16 16:28:43.907000','1층','jmc1111@naver.com','jmc1111','정민철','보은 양파','$2a$10$c0Ic5ThAB7MLOHOdS9K1CuFoEkXcLfF7fpzEDf1GbEAD/N4fJHwg6','01011119963',NULL,NULL,NULL,'ROLE_USER','경북 청송군 현동면 간동길 3','37456'),(22,'2023-02-16 17:10:23.965000','삼성화재유성연수원','mokbee@naver.com','mokbee','김태영','보은 양배추','$2a$10$t8V7s1LaC9lWNOxGqJ3tk.4g8PPWgAQjwdidyGGJrBwQCMxilvsKS','01012341234',NULL,NULL,NULL,'ROLE_USER','대전 유성구 동서대로 98-39','34153'),(23,'2023-02-16 19:04:12.864000','롯데캐슬 101동 1202호','csj1111@naver.com','csj1111','차세진','아삭한 포도','$2a$10$s9G/J.b8imnGqVtCgUOZ/edcxzKBYp4BjLpJbhxlXJS0UQ9hAwtjG','01055773154',NULL,NULL,NULL,'ROLE_USER','서울 송파구 올림픽로 269','05510'),(24,'2023-02-16 19:47:25.569000','101동101호','noongboo@gmail.coma','nongboo','이농부','부끄러운 상추','$2a$10$.ElquaBJPSNC5jcYwMdhR.CUI4S1rMellaSjneC8GSVj6cVn1gGfG','01012341234',NULL,NULL,NULL,'ROLE_USER','제주특별자치도 서귀포시 대정읍 대한로 632','63503'),(25,'2023-02-16 22:07:18.711000','삼성화재 연수원','sleepy@ssafy.com','sleepyfarm','이정규','부끄러운 쪽파','$2a$10$xa7brvJJ9puPAcc43dxUIeHjuYsyp9FRIqiZZ7846Ja2jwRvVFbsO','01012348765',NULL,NULL,NULL,'ROLE_USER','대전 유성구 동서대로 98-39','34153');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_image`
--

DROP TABLE IF EXISTS `member_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_image` (
  `member_image_id` bigint NOT NULL AUTO_INCREMENT,
  `original_name` varchar(255) DEFAULT NULL,
  `saved_path` varchar(255) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`member_image_id`),
  KEY `FKr9nbgi536kuu13spg7w3bbxow` (`member_id`),
  CONSTRAINT `FKr9nbgi536kuu13spg7w3bbxow` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_image`
--

LOCK TABLES `member_image` WRITE;
/*!40000 ALTER TABLE `member_image` DISABLE KEYS */;
INSERT INTO `member_image` VALUES (17,'alpaka.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/alpaka.jpg',1),(18,'pakaka.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/pakaka.jpg',11),(19,'크롱.jfif','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/%ED%81%AC%EB%A1%B1.jfif',18),(20,'012.png','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/012.png',5),(21,'apple22.png','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/apple22.png',20);
/*!40000 ALTER TABLE `member_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_info` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `delivery_status` varchar(255) DEFAULT NULL,
  `order_create_at` datetime(6) DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `pay_status` varchar(255) DEFAULT NULL,
  `tid` varchar(255) DEFAULT NULL,
  `total_order_price` int NOT NULL,
  `delivery_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKg9bvyk92yq2cxpbxxuoyry4qp` (`delivery_id`),
  KEY `FK1k2f3sqebjhppx7jo8dynykc9` (`member_id`),
  CONSTRAINT `FK1k2f3sqebjhppx7jo8dynykc9` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKg9bvyk92yq2cxpbxxuoyry4qp` FOREIGN KEY (`delivery_id`) REFERENCES `delivery_info` (`delivery_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_info`
--

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;
INSERT INTO `order_info` VALUES (1,'BSHIP','2023-02-17 00:24:02.361307','ORDER','BPAY','T3eec98f4a466e29ad8f',16000,NULL,14),(2,'BSHIP','2023-02-17 00:24:39.749365','ORDER','BPAY','T3eec94a003e7c98a4e6',34000,NULL,23),(3,'BSHIP','2023-02-17 00:27:16.856656','ORDER','BPAY',NULL,34000,NULL,1),(4,'BSHIP','2023-02-17 00:27:34.355953','ORDER','BPAY',NULL,12000,NULL,1),(5,'BSHIP','2023-02-17 00:33:15.182801','ORDER','BPAY','T3eecb4c003e7c98a4fe',16000,NULL,14),(6,'BSHIP','2023-02-17 00:38:04.036999','ORDER','BPAY','T3eecc6d4a466e29adaf',16000,NULL,14),(7,'BSHIP','2023-02-17 00:40:35.472586','ORDER','BPAY','T3eecd044a466e29adb8',8000,NULL,14),(8,'BSHIP','2023-02-17 00:52:25.729768','ORDER','BPAY','T3eecfcb003e7c98a539',8000,NULL,14),(9,'BSHIP','2023-02-17 00:54:37.272832','ORDER','BPAY','T3eed04f003e7c98a53f',8000,NULL,14),(10,'BSHIP','2023-02-17 00:57:55.566205','ORDER','BPAY','T3eed1154a466e29addf',8000,NULL,14),(11,'BSHIP','2023-02-17 00:59:05.197152','ORDER','BPAY','T3eed15b003e7c98a547',8000,NULL,14),(12,'BSHIP','2023-02-17 01:21:42.497524','ORDER','BPAY',NULL,8000,NULL,14),(13,'BSHIP','2023-02-17 01:22:21.578737','ORDER','BPAY','T3eed6ce003e7c98a597',34000,NULL,14),(14,'BSHIP','2023-02-17 02:03:55.351482','ORDER','BPAY','T3eee08d4a466e29aed5',34000,NULL,14),(15,'BSHIP','2023-02-17 02:04:34.051460','ORDER','BPAY','T3eee0b3003e7c98a62d',34000,NULL,5);
/*!40000 ALTER TABLE `order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `oitem_id` bigint NOT NULL AUTO_INCREMENT,
  `oitem_count` int NOT NULL,
  `oitem_created_at` datetime(6) DEFAULT NULL,
  `oitem_price` int NOT NULL,
  `store_num` bigint DEFAULT NULL,
  `tid` varchar(255) DEFAULT NULL,
  `item_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`oitem_id`),
  KEY `FKija6hjjiit8dprnmvtvgdp6ru` (`item_id`),
  KEY `FKi9h5ium1uah4jw57pg722osau` (`order_id`),
  CONSTRAINT `FKi9h5ium1uah4jw57pg722osau` FOREIGN KEY (`order_id`) REFERENCES `order_info` (`order_id`),
  CONSTRAINT `FKija6hjjiit8dprnmvtvgdp6ru` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,1,'2023-02-17 00:24:02.361250',16000,9,'T3eec98f4a466e29ad8f',12,1),(2,1,'2023-02-17 00:24:39.749342',34000,10,'T3eec94a003e7c98a4e6',10,2),(3,1,'2023-02-17 00:27:16.856631',34000,10,NULL,10,3),(4,1,'2023-02-17 00:27:34.355930',12000,9,NULL,11,4),(5,1,'2023-02-17 00:33:15.182780',16000,9,'T3eecb4c003e7c98a4fe',12,5),(6,1,'2023-02-17 00:38:04.036975',16000,9,'T3eecc6d4a466e29adaf',12,6),(7,1,'2023-02-17 00:40:35.472527',8000,9,'T3eecd044a466e29adb8',13,7),(8,1,'2023-02-17 00:52:25.729677',8000,9,'T3eecfcb003e7c98a539',13,8),(9,1,'2023-02-17 00:54:37.272805',8000,9,'T3eed04f003e7c98a53f',13,9),(10,1,'2023-02-17 00:57:55.566145',8000,9,'T3eed1154a466e29addf',13,10),(11,1,'2023-02-17 00:59:05.197094',8000,9,'T3eed15b003e7c98a547',13,11),(12,1,'2023-02-17 01:21:42.497450',8000,9,NULL,13,12),(13,1,'2023-02-17 01:22:21.578676',34000,10,'T3eed6ce003e7c98a597',10,13),(14,1,'2023-02-17 02:03:55.351421',34000,10,'T3eee08d4a466e29aed5',10,14),(15,1,'2023-02-17 02:04:34.051440',34000,10,'T3eee0b3003e7c98a62d',10,15);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `store_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `store_delivery_cost` int DEFAULT NULL,
  `store_delivery_free` int DEFAULT NULL,
  `store_description` varchar(255) NOT NULL,
  `store_detail_addr` varchar(50) NOT NULL,
  `store_img` varchar(255) NOT NULL,
  `store_name` varchar(15) NOT NULL,
  `store_phone_number` varchar(15) NOT NULL,
  `store_street_addr` varchar(50) NOT NULL,
  `store_zipcode` varchar(10) NOT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`store_id`),
  KEY `FK9ent0vb83viq2nar0gkdnav8y` (`member_id`),
  CONSTRAINT `FK9ent0vb83viq2nar0gkdnav8y` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (2,'2023-02-14 08:07:56.927000',2500,50000,'각종 계절과일 뿐만 아니라 열대과일까지 취급하고 있습니다. 언제나 뛰어난 품질을 약속드리겠습니다.','','.','후르츠 콰르텟','010-1421-3849','대전 유성구 동서대로 98-39','34153',2),(7,'2023-02-15 15:22:14.953000',3000,40000,'명품 무주 반딧불 사과를 취급합니다.','생활 농장','.','대차리 과수원','01012345123','전북 무주군 무주읍 서면길 30','55505',5),(9,'2023-02-16 05:12:57.303000',6000,40000,'저희는 농장에서 건강하게 키운 돼지만을 취급하는 돼지고기 전문점입니다.','돼지마을 돼지농장','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/chuksan.jpg','양평 돼지마을 생고기','03112341234','경기 양평군 서종면 가래골길 4','12504',11),(10,'2023-02-16 11:56:26.936000',6000,25000,'신선한 환영합니다. 제철과일을 판매하는 수통골 과일 청과 입니다.','삼성화재 유성 연수원','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/fruit.jpg','수통골 과일 청과','01045671234','대전 유성구 동서대로 98-39','34153',1),(11,'2023-02-16 15:09:14.979000',3000,50000,'전국최초 GAP인증! 꿀사과를 판매합니다.','1층','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/150301477.jpg','매현사과농장','01055883155','충북 충주시 가주농공1길 16','27479',9),(12,'2023-02-16 16:33:17.203000',3000,50000,'전국 최초 GAP 인증을 받은 꿀사과를 판매합니다.','1층','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/150301477.jpg','청송우가네','01055771129','경북 청송군 부남면 갈미길 8-1','37446',20),(13,'2023-02-16 17:11:11.596000',2500,50000,'각종 계절과일 뿐만 아니라 열대과일까지 취급하고 있습니다. 언제나 뛰어난 품질을 약속드리겠습니다.','삼성유성화재연수원','.','후르츠콰르텟','010-1421-3849','대전 유성구 동서대로 98-39','34153',22),(14,'2023-02-16 19:44:54.328000',0,0,'안녕하세요.','','.','제주맑은 귤 농원','','','',14),(15,'2023-02-16 19:50:51.866000',3000,40000,'제주의 싱싱한 농장물을 즐기는 공간, 맑은 제주 귤 농원 입니다','72번지','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/sss.jfif','맑은 제주 귤 농원','062-177-1888','제주특별자치도 서귀포시 대정읍 대한로 632','63503',24),(16,'2023-02-17 00:04:27.405000',3000,30000,'감귤 한 알로도 즐기는 상큼함, 내 몸에 비타민 충전! 계절별로 다른 품종의 귤들을 판매하고 있습니다.','1층','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/soonyinefarm.jpg','순이네 감귤','01051229980','제주특별자치도 서귀포시 가가로 44-7','63534',23);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_image`
--

DROP TABLE IF EXISTS `store_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_image` (
  `store_image_id` bigint NOT NULL AUTO_INCREMENT,
  `original_name` varchar(255) DEFAULT NULL,
  `saved_path` varchar(255) DEFAULT NULL,
  `store_id` bigint DEFAULT NULL,
  PRIMARY KEY (`store_image_id`),
  KEY `FK8i0t3yr73c9h244pyv5mg6m4u` (`store_id`),
  CONSTRAINT `FK8i0t3yr73c9h244pyv5mg6m4u` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_image`
--

LOCK TABLES `store_image` WRITE;
/*!40000 ALTER TABLE `store_image` DISABLE KEYS */;
INSERT INTO `store_image` VALUES (9,'chuksan.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/chuksan.jpg',9),(10,'fruit.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/fruit.jpg',10),(11,'150301477.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/150301477.jpg',11),(12,'150301477.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/150301477.jpg',12),(13,'sss.jfif','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/sss.jfif',15),(14,'soonyinefarm.jpg','https://farmcu-bucket.s3.ap-northeast-2.amazonaws.com/soonyinefarm.jpg',16);
/*!40000 ALTER TABLE `store_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_like`
--

DROP TABLE IF EXISTS `store_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_like` (
  `storelike_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint DEFAULT NULL,
  `store_id` bigint DEFAULT NULL,
  PRIMARY KEY (`storelike_id`),
  KEY `FKgsdtstadvjlrhepemkl3dmsq7` (`member_id`),
  KEY `FKi4remkbjs1xpwdpfu8chweius` (`store_id`),
  CONSTRAINT `FKgsdtstadvjlrhepemkl3dmsq7` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKi4remkbjs1xpwdpfu8chweius` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_like`
--

LOCK TABLES `store_like` WRITE;
/*!40000 ALTER TABLE `store_like` DISABLE KEYS */;
INSERT INTO `store_like` VALUES (1,1,10),(2,1,15);
/*!40000 ALTER TABLE `store_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_refresh_token`
--

DROP TABLE IF EXISTS `user_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_refresh_token` (
  `refresh_token_seq` bigint NOT NULL AUTO_INCREMENT,
  `id` varchar(512) DEFAULT NULL,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`refresh_token_seq`),
  UNIQUE KEY `UK_9la7hwy8ckngxf2q0p9qr7vj3` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_refresh_token`
--

LOCK TABLES `user_refresh_token` WRITE;
/*!40000 ALTER TABLE `user_refresh_token` DISABLE KEYS */;
INSERT INTO `user_refresh_token` VALUES (1,'myfarm','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE5OTUxN30.4tc9yHNaWvJN1qr05bFjsOHuxnZ6DOMl1N2g2aJDhBY'),(2,'kty_test','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE4NDE3NX0.IX0oGZ-K52E0uKeJYulqdI4RxORMHHyjSz9ugyn5xFM'),(3,'chacha','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzAwMTQ3Nn0.W_tkabVhEdeA6XlqOH9DOfAccyEvoAIt8b7DAfTxdFY'),(4,'myfarmy','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzAwMjU3NH0.9d4wMjLelOgAE2x2fl0GUGuZM3duUPY7jguRFLVRkj4'),(5,'Jihee','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzIwNDIzNn0.FXTVbTZm6kJrsz3Y4KLFBXSCVd84qvqraRARYnsnug4'),(6,'Jisung','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzAwMzkxNH0.W0ST4n-RAptpTm8cpGNPuRmttpUXZi78H28-AxtfKXo'),(7,'ljs3112','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzAxOTg3M30.ao8mhbGtdLvThYUgjcr3fW4Hgr0MQLTL84UAztFphNA'),(8,'kakao-2665488439','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE3MDk1OH0.v5pWCaU0LXdk8KLKSxTIc2_U6LRPcbM0vqEHGYvYLkI'),(9,'test12211','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE3ODQyMX0.rHi2-z58Hkn7mN47CntROadr_yYWJBOMuL5O04P5dSA'),(10,'asas','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzEzODM1M30.QyQ8ud8ET0nSaq7npk786wudIZ0GFHmK7NeEn05gr9E'),(11,'funfarm','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE5NTU2NH0.01qVJ3KOncfcrq4I9WP9dJH3rFZtEmNrLAeTA3slZGw'),(12,'test1166','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzA1NDQxOX0.acUJ1qTx4j18tRLOJw7kDi9TEKBOHJ2kRHzg1C31moA'),(13,'test9911','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzA0NzQ1Nn0.30-h7iTNBrQ7osox0yivR3jAyE7q1aVjvEbh2mroACs'),(14,'sungsim','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE5ODAwMX0.VAgT4Ja87iJqWfFbsYHARxDuKkHAG87jQBi_wVpGtEc'),(15,'juicyFruit','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzEzNzU4Nn0.6mzPd0HBaTTLdGPQoy8Ujrg6aOkqM4k02uyxAAqytMU'),(16,'sjk0000','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzEzODQ2N30.g6mmtsYoajF1SN9hfeb4QSIM2cC0RIT6y3bJbEX2k_c'),(17,'eon9999','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE1NDE0NH0.auI8A5ge0D4a87yztoAFziTfq9egb4_HiAU7ij6w-IM'),(18,'smartpodo','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE1OTE3NH0.BSiQmihpHnHQd4SjUTpFcf9HHlABn_sWXUDV8mra99M'),(19,'jmc1111','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE2OTczNH0.mDyZxQv20SOynMq8zoDQZ78T5wvotSQSfqiWbb_ieQE'),(20,'mokbee','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE3OTQ5N30.O32nlOqeLMN9kRTTqjU13vDHNwb9vWKxkzVm8iSBS3U'),(21,'csj1111','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE5NjgyOH0.DHWsujwePZPy2QqgOPd0CUKerkkdCV5IwMEjDBn9byI'),(22,'nongboo','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE5MjYxM30.Yl8IR_OX6vnfWwbraNMUqcDwflbRffF9SM5DPEc03pY'),(23,'sleepyfarm','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY3NzE5MDU4OH0.8LCuoQ8ZicxJR0UKIBEp1_jDwJVLqEazlznwQRaiab0');
/*!40000 ALTER TABLE `user_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17 11:07:05
