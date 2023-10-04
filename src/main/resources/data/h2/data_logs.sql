INSERT INTO REQUEST_LOGS (ID, 
						  TIME_STAMP, 
						  IP, 
						  METHOD,
						  RESOURCE, 
						  STATUS_CODE,
						  CONTENT_TYPE,
						  ELAPSED_TIME) VALUES

(100, {ts '2023-01-20 18:00:00.89'}, '10.250.0.5', 'GET', '/pedidos', 200, 'application/json', 245),
(101, {ts '2023-01-20 18:00:10.02'}, '10.250.0.5', 'GET', '/productos', 200, 'application/json', 34),
(102, {ts '2023-01-20 18:00:50.14'}, '10.250.0.7', 'GET', '/productos', 200, 'application/json', 67),
(103, {ts '2023-01-20 18:04:12.89'}, '10.250.9.2', 'POST', '/productos', 201, 'application/json', 120),
(104, {ts '2023-01-20 18:04:20.12'}, '11.253.2.8', 'PUT', '/productos', 500, 'application/json', 12102),
(105, {ts '2023-01-20 18:05:00.56'}, '10.250.1.10', 'GET', '/productos', 404, 'application/json', 8);
