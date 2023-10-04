INSERT INTO REQUEST_LOGS (ID, 
						  TIME_STAMP, 
						  IP, 
						  METHOD,
						  RESOURCE, 
						  STATUS_CODE,
						  CONTENT_TYPE,
						  ELAPSED_TIME) VALUES

(100, '2020-10-23', '10.250.0.5', 'GET', '/pedidos', 200, 'application/json', 245),
(101, '2020-10-23', '10.250.0.5', 'GET', '/productos', 200, 'application/json', 33);
