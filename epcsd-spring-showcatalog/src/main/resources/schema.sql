INSERT INTO category (name, description)
VALUES ('Teatro', 'Obras de teatro, musicales, drama, comedia...')
     , ('Concierto', 'Conciertos de música clásica, pop, rock, etc.')
     , ('Danza', 'Representaciones de danza clásica, contemporánea...')
     , ('Ópera', 'Ópera')
     , ('Circo', 'Actuaciones de circo')
     , ('Monólogo', 'Monólogos, stand-up comedy, improvisación, etc.');

INSERT INTO show (name, id_category, description, image, price, duration, capacity, on_sale_date, status)
VALUES ('Cats', 1, 'Musical Cats', 'https://en.wikipedia.org/wiki/Cats_(musical)#/media/File:CatsMusicalLogo.jpg', 20,
        120, 300, '2022-05-01', 0)
     , ('Macbeth', 1, 'MacBeth by Shakespeare', 'https://en.wikipedia.org/wiki/File:First-page-first-folio-macbeth.jpg',
        30, 180, 350, '2022-06-15', 0)
     , ('Concierto de año nuevo', 2, 'Concierto de música clásica con ocasión del año nuevo 2023',
        'http://foo.bar/image.jpg', 15, 120, 250, '2022-11-01', 0)
     , ('Las 4 estaciones', 2, 'Las 4 estaciones de Vivaldi', 'http://foo.bar/image.jpg', 17, 90, 200, '2022-11-01', 0)

     , ('El lago de los cisnes', 3, 'Interpretado por la compañía de danza de San Petersburgo',
        'https://es.wikipedia.org/wiki/El_lago_de_los_cisnes#/media/Archivo:Wilfride_Piollet_danse_dans_%22le_Lac_des_Cygnes%22_(%C3%A0_l''Op%C3%A9ra_de_Paris,_1977).jpg',
        40, 180, 325, '2022-12-05', 0)
     , ('El cascanueces', 3, 'Interpretado por la compañía de danza de Sebastopol', 'http://foo.bar/image.jpg', 38, 120,
        300, '2022-09-18', 0)

     , ('Così fan tutte', 4, 'Ciclo Mozart en el Palau de la Musica',
        'https://es.wikipedia.org/wiki/Cos%C3%AC_fan_tutte#/media/Archivo:Cosi_fan_tutte_-_first_performance.jpg', 24,
        110, 225, '2023-01-12', 0)
     , ('Rigoletto', 4, 'Verdi al Liceu', 'http://foo.bar/image.jpg', 42, 100, 150, '2022-08-01', 0)

     , ('Circo Raluy', 5, 'El Circo Raluy visita Barcelona', 'http://foo.bar/image.jpg', 10, 120, 400, '2022-06-01', 0)
     , ('Circo Mundial', 5, 'Gira de verano del Circo Mundial - Ahora SIN leones!', 'http://foo.bar/image.jpg', 12, 100,
        300, '2022-08-10', 0)

     , ('Vermunólogos', 6, 'Diferentes artistas cada día!', 'http://foo.bar/image.jpg', 5, 45, 100, '2022-06-15', 0)
     , ('Tinder sorpresa', 6, 'El nuevo espectáculo de Toni Moog', 'http://foo.bar/image.jpg', 15, 90, 200,
        '2022-07-20', 0)
     , ('El club de la comedia', 6, 'Actuación en vivo del aclamado programa de humor', 'http://foo.bar/image.jpg', 20,
        90, 300, '2022-10-05', 0)
;


INSERT INTO performance (id_show, date, time, streaming_url, remaining_seats, status)
values (1, '2022-05-15', '21:30', 'http://foo.bar', 300, 0)
     , (1, '2022-05-18', '21:30', 'http://foo.bar', 300, 0)
     , (2, '2022-07-25', '22:00', 'http://foo.bar', 350, 0)
;