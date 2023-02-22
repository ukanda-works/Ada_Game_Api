insert into usuario (puntuacion, userName, email, password, description, country, last_login)
            VALUES (100, 'juan', 'juan@example.com', 'password123','hola soy juan', 'España', localtimestamp);
INSERT INTO usuario (puntuacion, userName, email, password, country, last_login) VALUES (50, 'maria', 'maria@example.com', 'password456', 'México', localtimestamp);
INSERT INTO usuario (puntuacion, userName, email, password, description, last_login) VALUES (75, 'pedro', 'pedro@example.com', 'password789', 'Soy un amante de los deportes.', localtimestamp);
INSERT INTO usuario (puntuacion, userName, email, password, country, last_login) VALUES (70, 'isabel', 'isabel@example.com', 'passwordabc', 'Chile', localtimestamp);

insert into party (resultado, jugador_color, date, user_id)
            VALUES ('blanco', 'blanco', localtimestamp , 1);

    INSERT INTO party (resultado, jugador_color, date, user_id) VALUES ('negro', 'blanco', localtimestamp, 2);
    INSERT INTO party (resultado, jugador_color, date, user_id) VALUES ('blanco', 'negro', localtimestamp, 3);
    INSERT INTO party (resultado, jugador_color, date, user_id) VALUES ('empate', 'blanco', localtimestamp, 4);
    INSERT INTO party (resultado, jugador_color, date, user_id) VALUES ('negro', 'negro', localtimestamp, 1);
    INSERT INTO party (resultado, jugador_color, date, user_id) VALUES ('blanco', 'blanco', localtimestamp, 3);
    INSERT INTO party (resultado, jugador_color, date, user_id) VALUES ('empate', 'negro', localtimestamp, 2);
    INSERT INTO party (resultado, jugador_color, date, user_id) VALUES ('blanco', 'blanco', localtimestamp, 4);
    INSERT INTO party (resultado, jugador_color, date, user_id) VALUES ('negro', 'negro', localtimestamp, 3);
    INSERT INTO party (resultado, jugador_color, date, user_id) VALUES ('blanco', 'negro', localtimestamp, 1);
    INSERT INTO party (resultado, jugador_color, date, user_id) VALUES ('empate', 'negro', localtimestamp, 4);

INSERT INTO turn (numTurno, jugador, movimiento, party_id) VALUES (1, 'blanco', 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1', 1);
INSERT INTO turn (numTurno, jugador, movimiento, party_id) VALUES (2, 'negro', 'rnbqkbnr/pppppppp/8/8/8/2N5/PPPPPPPP/R1BQKBNR b KQkq - 1 1', 1);
INSERT INTO turn (numTurno, jugador, movimiento, party_id) VALUES (3, 'blanco', 'rnbqkbnr/pppppppp/8/8/3P4/2N5/PPP1PPPP/R1BQKBNR b KQkq d3 0 2', 1);
INSERT INTO turn (numTurno, jugador, movimiento, party_id) VALUES (4, 'negro', 'rnbqkbnr/ppppp1pp/5p2/8/3P4/2N5/PPP1PPPP/R1BQKBNR w KQkq - 0 3', 1);
INSERT INTO turn (numTurno, jugador, movimiento, party_id) VALUES (5, 'blanco', 'rnbqkbnr/ppppp1pp/5p2/8/3P4/2N2N2/PPP1PPPP/R1BQKB1R b KQkq - 1 3', 1);
INSERT INTO turn (numTurno, jugador, movimiento, party_id) VALUES (6, 'negro', 'rnbqkbnr/pppp2pp/4pp2/8/3P4/2N2N2/PPP1PPPP/R1BQKB1R w KQkq e6 0 4', 1);
INSERT INTO turn (numTurno, jugador, movimiento, party_id) VALUES (7, 'blanco', 'rnbqkbnr/pppp2pp/4pp2/8/3P4/2N2N2/PPP1PPPP/R1BQK2R b KQkq - 1 4', 1);
INSERT INTO turn (numTurno, jugador, movimiento, party_id) VALUES (8, 'negro', 'rnbqkbnr/pppp2pp/4pp2/8/3P4/2N5/PPP1PPPP/R1BQK2R b KQkq - 0 5', 1);
INSERT INTO turn (numTurno, jugador, movimiento, party_id) VALUES (9, 'blanco', 'rnbqkbnr/pppp2pp/4pp2/8/3P4/2N2N2/PPP1PPPP/R1BQK2R b KQkq - 1 5', 1);