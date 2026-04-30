-- =============================================
-- SEED DATA — insert order matters (FK dependencies)
-- =============================================

-- 1. USERS
INSERT INTO Users (user_id, password, email, name, bio) VALUES
                                                            (1, 'pass1234', 'alice@email.com',    'alice',    'caffeine-powered developer'),
                                                            (2, 'pass1234', 'bob@email.com',      'bob',      'hiking and hot takes'),
                                                            (3, 'pass1234', 'charlie@email.com',  'charlie',  'night owl. code goblin.'),
                                                            (4, 'pass1234', 'diana@email.com',    'diana',    'photographer + dog mom'),
                                                            (5, 'pass1234', 'eve@email.com',      'eve',      'math nerd, music snob'),
                                                            (6, 'pass1234', 'frank@email.com',    'frank',    'aspiring chef'),
                                                            (7, 'pass1234', 'grace@email.com',    'grace',    'design + frontend'),
                                                            (8, 'pass1234', 'hank@email.com',     'hank',     'gym rat');

-- 2. POSTS (parent posts)
INSERT INTO Post (post_id, user_id, parent_post_id, numOfReplies, numOfLikes, numOfShares, created_at) VALUES
                                                                                                           (1,  1, NULL, 3, 5, 2, '2026-04-10 09:15:00'),
                                                                                                           (2,  1, NULL, 1, 3, 0, '2026-04-12 14:30:00'),
                                                                                                           (3,  2, NULL, 2, 4, 1, '2026-04-10 11:00:00'),
                                                                                                           (4,  2, NULL, 0, 2, 0, '2026-04-14 07:45:00'),
                                                                                                           (5,  3, NULL, 1, 6, 3, '2026-04-11 03:20:00'),
                                                                                                           (6,  3, NULL, 2, 2, 0, '2026-04-15 02:10:00'),
                                                                                                           (7,  4, NULL, 1, 7, 4, '2026-04-11 18:00:00'),
                                                                                                           (8,  4, NULL, 0, 3, 1, '2026-04-16 10:30:00'),
                                                                                                           (9,  5, NULL, 2, 4, 1, '2026-04-12 20:00:00'),
                                                                                                           (10, 6, NULL, 1, 5, 2, '2026-04-13 12:00:00'),
                                                                                                           (11, 7, NULL, 0, 3, 0, '2026-04-14 16:45:00'),
                                                                                                           (12, 8, NULL, 1, 2, 0, '2026-04-15 06:30:00');

-- POSTS (replies)
INSERT INTO Post (post_id, user_id, parent_post_id, numOfReplies, numOfLikes, numOfShares, created_at) VALUES
                                                                                                           (13, 2, 1, 0, 2, 0, '2026-04-10 09:30:00'),
                                                                                                           (14, 3, 1, 0, 1, 0, '2026-04-10 09:45:00'),
                                                                                                           (15, 5, 1, 0, 3, 0, '2026-04-10 10:00:00'),
                                                                                                           (16, 1, 3, 0, 1, 0, '2026-04-10 12:00:00'),
                                                                                                           (17, 4, 3, 0, 2, 0, '2026-04-10 13:30:00'),
                                                                                                           (18, 6, 2, 0, 0, 0, '2026-04-12 15:00:00'),
                                                                                                           (19, 7, 5, 0, 1, 0, '2026-04-11 08:00:00'),
                                                                                                           (20, 1, 6, 0, 0, 0, '2026-04-15 02:30:00'),
                                                                                                           (21, 8, 6, 0, 1, 0, '2026-04-15 03:00:00'),
                                                                                                           (22, 5, 7, 0, 2, 0, '2026-04-11 19:00:00'),
                                                                                                           (23, 3, 9, 0, 1, 0, '2026-04-12 21:00:00'),
                                                                                                           (24, 6, 9, 0, 0, 0, '2026-04-12 22:30:00'),
                                                                                                           (25, 4, 10, 0, 1, 0, '2026-04-13 13:00:00'),
                                                                                                           (26, 2, 12, 0, 0, 0, '2026-04-15 07:00:00');

-- 3. POST CONTENT
INSERT INTO PostContent (content_id, post_id, content_type, text_content, media_url) VALUES
                                                                                         (1,  1,  'text', 'just deployed my first spring boot app. only cried twice.',               NULL),
                                                                                         (2,  2,  'text', 'hot take: postgresql is the best database and its not even close',         NULL),
                                                                                         (3,  3,  'text', 'summited camelback at sunrise. almost passed out but the views were worth it', NULL),
                                                                                         (4,  4,  'text', 'woke up at 5am voluntarily. who even am i anymore',                       NULL),
                                                                                         (5,  5,  'text', 'its 3am and i just discovered my entire codebase has a typo in every variable name. shipping it anyway.', NULL),
                                                                                         (6,  6,  'text', 'should i learn rust or go next? wrong answers only',                      NULL),
                                                                                         (7,  7,  'text', 'golden hour in sedona. no filter needed when the sky does this',           NULL),
                                                                                         (8,  8,  'text', 'my dog learned to open the fridge. we are in the endgame now.',            NULL),
                                                                                         (9,  9,  'text', 'euler identity is proof that math is just poetry with extra steps',        NULL),
                                                                                         (10, 10, 'text', 'made homemade pasta for the first time. life will never be the same',      NULL),
                                                                                         (11, 11, 'text', 'redesigned my portfolio for the 47th time. this is the one. (it is not the one.)', NULL),
                                                                                         (12, 12, 'text', 'new pr on deadlift today. the grind never stops',                         NULL),
                                                                                         (13, 13, 'text', 'only twice? thats actually impressive for spring boot',                    NULL),
                                                                                         (14, 14, 'text', 'welcome to the club. the errors only get weirder from here',               NULL),
                                                                                         (15, 15, 'text', 'what stack? i need to know everything',                                    NULL),
                                                                                         (16, 16, 'text', 'how early did you start?? i can barely make it to 9am lectures',           NULL),
                                                                                         (17, 17, 'text', 'the desert sunrise hits different. need to go back',                       NULL),
                                                                                         (18, 18, 'text', 'postgresql supremacy. this is the way.',                                   NULL),
                                                                                         (19, 19, 'text', 'this is either genius or unhinged. respect either way',                    NULL),
                                                                                         (20, 20, 'text', 'rust. always rust. the borrow checker builds character',                   NULL),
                                                                                         (21, 21, 'text', 'go is way easier to pick up honestly. rust is a time commitment',          NULL),
                                                                                         (22, 22, 'text', 'sedona is unreal. how do you even capture that light??',                   NULL),
                                                                                         (23, 23, 'text', 'euler identity walked so complex analysis could run',                      NULL),
                                                                                         (24, 24, 'text', 'have you read a mathematicians apology? youd love it',                     NULL),
                                                                                         (25, 25, 'text', 'ok recipe or it didnt happen',                                             NULL),
                                                                                         (26, 26, 'text', 'what program are you running? been looking for a new split',               NULL);

-- 4. POST LIKES
INSERT INTO PostLike (post_id, user_id, created_at) VALUES
                                                        (1, 2, '2026-04-10 09:20:00'),
                                                        (1, 3, '2026-04-10 09:25:00'),
                                                        (1, 5, '2026-04-10 10:05:00'),
                                                        (1, 6, '2026-04-10 11:00:00'),
                                                        (1, 7, '2026-04-10 14:00:00'),
                                                        (2, 3, '2026-04-12 14:45:00'),
                                                        (2, 5, '2026-04-12 15:30:00'),
                                                        (2, 6, '2026-04-12 16:00:00'),
                                                        (3, 1, '2026-04-10 11:30:00'),
                                                        (3, 4, '2026-04-10 12:00:00'),
                                                        (3, 7, '2026-04-10 13:00:00'),
                                                        (3, 8, '2026-04-10 15:00:00'),
                                                        (4, 1, '2026-04-14 08:00:00'),
                                                        (4, 3, '2026-04-14 09:00:00'),
                                                        (5, 1, '2026-04-11 07:00:00'),
                                                        (5, 2, '2026-04-11 08:30:00'),
                                                        (5, 4, '2026-04-11 09:00:00'),
                                                        (5, 6, '2026-04-11 10:00:00'),
                                                        (5, 7, '2026-04-11 08:15:00'),
                                                        (5, 8, '2026-04-11 11:00:00'),
                                                        (6, 1, '2026-04-15 02:25:00'),
                                                        (6, 2, '2026-04-15 06:00:00'),
                                                        (7, 1, '2026-04-11 18:30:00'),
                                                        (7, 2, '2026-04-11 19:00:00'),
                                                        (7, 3, '2026-04-11 19:15:00'),
                                                        (7, 5, '2026-04-11 19:30:00'),
                                                        (7, 6, '2026-04-11 20:00:00'),
                                                        (7, 7, '2026-04-11 20:30:00'),
                                                        (7, 8, '2026-04-11 21:00:00'),
                                                        (8, 1, '2026-04-16 11:00:00'),
                                                        (8, 3, '2026-04-16 12:00:00'),
                                                        (8, 6, '2026-04-16 13:00:00'),
                                                        (9, 1, '2026-04-12 20:30:00'),
                                                        (9, 3, '2026-04-12 21:15:00'),
                                                        (9, 4, '2026-04-12 22:00:00'),
                                                        (9, 7, '2026-04-12 23:00:00'),
                                                        (10, 1, '2026-04-13 12:30:00'),
                                                        (10, 2, '2026-04-13 13:00:00'),
                                                        (10, 4, '2026-04-13 13:30:00'),
                                                        (10, 5, '2026-04-13 14:00:00'),
                                                        (10, 8, '2026-04-13 15:00:00'),
                                                        (11, 1, '2026-04-14 17:00:00'),
                                                        (11, 3, '2026-04-14 18:00:00'),
                                                        (11, 5, '2026-04-14 19:00:00'),
                                                        (12, 2, '2026-04-15 07:00:00'),
                                                        (12, 6, '2026-04-15 08:00:00');

-- 5. POST SHARES
INSERT INTO PostShare (post_id, user_id, created_at) VALUES
                                                         (1, 5, '2026-04-10 10:30:00'),
                                                         (1, 7, '2026-04-10 15:00:00'),
                                                         (3, 1, '2026-04-10 12:30:00'),
                                                         (5, 2, '2026-04-11 09:00:00'),
                                                         (5, 7, '2026-04-11 09:30:00'),
                                                         (5, 8, '2026-04-11 12:00:00'),
                                                         (7, 1, '2026-04-11 19:00:00'),
                                                         (7, 2, '2026-04-11 19:30:00'),
                                                         (7, 5, '2026-04-11 20:00:00'),
                                                         (7, 8, '2026-04-11 21:30:00'),
                                                         (8, 5, '2026-04-16 12:00:00'),
                                                         (9, 6, '2026-04-12 23:00:00'),
                                                         (10, 7, '2026-04-13 14:00:00'),
                                                         (10, 8, '2026-04-13 16:00:00');

-- 6. FOLLOWS
INSERT INTO Follows (follower_key, followed_key) VALUES
                                                     (1, 2), (1, 3), (1, 4),
                                                     (2, 1), (2, 4), (2, 5),
                                                     (3, 1), (3, 5),
                                                     (4, 1), (4, 5), (4, 7),
                                                     (5, 1), (5, 3),
                                                     (6, 1), (6, 2), (6, 4),
                                                     (7, 3), (7, 4), (7, 6),
                                                     (8, 2), (8, 6);

-- 7. RESET SEQUENCES
SELECT setval('users_user_id_seq', (SELECT MAX(user_id) FROM Users));
SELECT setval('post_post_id_seq', (SELECT MAX(post_id) FROM Post));
SELECT setval('postcontent_content_id_seq', (SELECT MAX(content_id) FROM PostContent));