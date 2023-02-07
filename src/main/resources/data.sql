INSERT INTO accounts (login_id, name, password)
VALUES
    ('system', 'SYSTEM', '$2a$10$QtAKrR0MkrS2RrY9ZwJofOzQCMeYfoEEG4sYI5ivh8CIhiHOFPoMO');

INSERT INTO delivery(request_time, end_time, address, accounts_id, status)
VALUES
    ('2023-02-07 12:58:57', '2023-02-08 01:58:57', '서울시 천호구', 1, 'COMPLETED')
    , ('2023-02-08 12:58:57', null, '서울시 천호구', 1, 'IN_DELIVERY')
    , ('2023-02-09 12:58:57', null, '서울시 천호구', 1, 'WAITING')
;