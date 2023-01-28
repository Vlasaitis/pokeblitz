INSERT INTO player (ID, COINS, ELO, EMAIL, LOSSES, PASSWORD, USERNAME, WINS)
VALUES (2, 400, 1000, '', 0, '', '', 0);

INSERT INTO player (ID, COINS, ELO, EMAIL, LOSSES, PASSWORD, USERNAME, WINS)
VALUES (3, 400, 1000, '', 0, '', '', 0);

INSERT INTO player (ID, COINS, ELO, EMAIL, LOSSES, PASSWORD, USERNAME, WINS)
VALUES (4, 200, 1000,'' , 0, '', '', 0);


INSERT INTO battle_pokemon (ID, ATTACK, DAMAGE_DONE, DEFENSE, DOUBLE_DAMAGE, HALF_DAMAGE, HAS_TURN, IMAGE, MAX_HP, NAME, POWER_LEVEL, SPEED, TYPES, PLAYER_ID) VALUES (21, 55, 0, 50, '', 'rock, steel', 'TRUE', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/133.png', 55, 'EEVEE', 215, 55, 'normal', 2);
INSERT INTO battle_pokemon (ID, ATTACK, DAMAGE_DONE, DEFENSE, DOUBLE_DAMAGE, HALF_DAMAGE, HAS_TURN, IMAGE, MAX_HP, NAME, POWER_LEVEL, SPEED, TYPES, PLAYER_ID) VALUES (22, 75, 0, 35, 'ground, rock, water, grass, fairy', 'flying, poison, bug, steel, fire, grass, dragon, poison, ground, rock, ghost', TRUE, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/69.png', 50, 'BELLSPROUT', 200, 40, '[grass, poison]', 2);
INSERT INTO battle_pokemon (ID, ATTACK, DAMAGE_DONE, DEFENSE, DOUBLE_DAMAGE, HALF_DAMAGE, HAS_TURN, IMAGE, MAX_HP, NAME, POWER_LEVEL, SPEED, TYPES, PLAYER_ID) VALUES (23, 55, 0, 50, 'grass, psychic, dark, grass, fairy', 'fighting, flying, poison, ghost, steel, fire, fairy, poison, ground, rock, ghost', TRUE, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/48.png', 60, 'VENONAT', 210, 45, '[bug, poison]', 2);
INSERT INTO battle_pokemon (ID, ATTACK, DAMAGE_DONE, DEFENSE, DOUBLE_DAMAGE, HALF_DAMAGE, HAS_TURN, IMAGE, MAX_HP, NAME, POWER_LEVEL, SPEED, TYPES, PLAYER_ID) VALUES (24, 55, 0, 50, '', 'rock, steel', TRUE, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/133.png', 55, 'EEVEE', 215, 55, 'normal', 3);
INSERT INTO battle_pokemon (ID, ATTACK, DAMAGE_DONE, DEFENSE, DOUBLE_DAMAGE, HALF_DAMAGE, HAS_TURN, IMAGE, MAX_HP, NAME, POWER_LEVEL, SPEED, TYPES, PLAYER_ID) VALUES (25, 52, 0, 48, 'ground, rock, fire', 'water, grass, dragon', TRUE, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/54.png', 50, 'PSYDUCK', 205, 55, 'water', 3);
INSERT INTO battle_pokemon (ID, ATTACK, DAMAGE_DONE, DEFENSE, DOUBLE_DAMAGE, HALF_DAMAGE, HAS_TURN, IMAGE, MAX_HP, NAME, POWER_LEVEL, SPEED, TYPES, PLAYER_ID) VALUES (26, 57, 0, 40, 'grass, fairy', 'poison, ground, rock, ghost', TRUE, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/32.png', 46, 'NIDORAN-M', 193, 50, 'poison', 3);
INSERT INTO battle_pokemon (ID, ATTACK, DAMAGE_DONE, DEFENSE, DOUBLE_DAMAGE, HALF_DAMAGE, HAS_TURN, IMAGE, MAX_HP, NAME, POWER_LEVEL, SPEED, TYPES, PLAYER_ID) VALUES (27, 64, 0, 45, 'dragon', 'steel', TRUE, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/147.png', 41, 'DRATINI', 200, 50, 'dragon', 4);
INSERT INTO battle_pokemon (ID, ATTACK, DAMAGE_DONE, DEFENSE, DOUBLE_DAMAGE, HALF_DAMAGE, HAS_TURN, IMAGE, MAX_HP, NAME, POWER_LEVEL, SPEED, TYPES, PLAYER_ID) VALUES (28, 45, 0, 35, '', 'rock, steel', TRUE, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/52.png', 40, 'MEOWTH', 210, 90, 'normal', 4);
INSERT INTO battle_pokemon (ID, ATTACK, DAMAGE_DONE, DEFENSE, DOUBLE_DAMAGE, HALF_DAMAGE, HAS_TURN, IMAGE, MAX_HP, NAME, POWER_LEVEL, SPEED, TYPES, PLAYER_ID) VALUES (29,30,0,50,'flying, water','grass, electric, dragon',TRUE,'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/100.png',40,'VOLTORB',220,100,'electric',4);

INSERT INTO STARTERS (ID, BATTLE_POKEMON1_ID, BATTLE_POKEMON2_ID, BATTLE_POKEMON3_ID, PLAYER_ID) VALUES (2, 21, 22, 23 , 2);
INSERT INTO STARTERS (ID, BATTLE_POKEMON1_ID, BATTLE_POKEMON2_ID, BATTLE_POKEMON3_ID, PLAYER_ID) VALUES (3, 24, 25, 26 , 3);
INSERT INTO STARTERS (ID, BATTLE_POKEMON1_ID, BATTLE_POKEMON2_ID, BATTLE_POKEMON3_ID, PLAYER_ID) VALUES (4, 27, 28, 29 , 4);
