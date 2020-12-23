INSERT INTO COMPUTERS (id, id_room, id_place, status, writeoff_date) VALUES
  (1, 1, 1, 'work', null),
  (2, 1, 2, 'work', null),
  (3, 1, 3, 'work', null),
  (4, 1, 4, 'work', null),
  (5, 1, 5, 'work', null),
  (6, 1, 6, 'work', null),
  (7, 2, 1, 'work', null),
  (8, 2, 2, 'work', null),
  (9, 2, 3, 'work', null),
  (10, 2, 4, 'work', null),
  (11, 2, 5, 'work', null),
  (12, 2, 6, 'work', null);

INSERT INTO ROOMS (id, last_inventory_date, next_inventory_date, work_equipment) VALUES
  (1, '10/07/2020','10/07/2021', 0),
  (2, '12/07/2020','12/07/2021', 0);