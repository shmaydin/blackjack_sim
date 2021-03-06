class Basic_Stratagy:
    #PLAYER_TOTAL[total value of players cards][dealers face up card]
    PLAYER_TOTAL = {
                    8:['-1', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    9:['-1', 'H', 'H', 'D', 'D', 'D', 'D', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    10:['-1', 'H', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'H', 'H', 'H', 'H'],
                    11:['-1', 'H', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'],
                    12:['-1', 'H', 'H', 'H', 'S', 'S', 'S', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    13:['-1', 'H', 'S', 'S', 'S', 'S', 'S', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    14:['-1', 'H', 'S', 'S', 'S', 'S', 'S', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    15:['-1', 'H', 'S', 'S', 'S', 'S', 'S', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    16:['-1', 'H', 'S', 'S', 'S', 'S', 'S', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    17:['-1', 'H', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S']
                    }


    PLAYER_ACE = {
                    2:['-1', 'H', 'H', 'H', 'H', 'D', 'D', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    3:['-1', 'H', 'H', 'H', 'H', 'D', 'D', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    4:['-1', 'H', 'H', 'H', 'D', 'D', 'D', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    5:['-1', 'H', 'H', 'H', 'D', 'D', 'D', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    6:['-1', 'H', 'H', 'D', 'D', 'D', 'D', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    7:['-1', 'H', 'S', 'D', 'D', 'D', 'D', 'S', 'S', 'H', 'H', 'H', 'H', 'H'],
                    8:['-1', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S'],
                    9:['-1', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S']
                }


    PLAYER_PAIR = {
                    1:['-1', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP'],
                    2:['-1', 'H', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'H', 'H', 'H', 'H', 'H', 'H'],
                    3:['-1', 'H', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'H', 'H', 'H', 'H', 'H', 'H'],
                    4:['-1', 'H', 'H', 'H', 'H', 'SP', 'SP', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    5:['-1', 'H', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'H', 'H', 'H', 'H'],
                    6:['-1', 'H', 'SP', 'SP', 'SP', 'SP', 'SP', 'H', 'H', 'H', 'H', 'H', 'H', 'H'],
                    7:['-1', 'H', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'H', 'H', 'H', 'H', 'H', 'H'],
                    8:['-1', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP', 'SP'],
                    9:['-1', 'S', 'SP', 'SP', 'SP', 'SP', 'SP', 'S', 'SP', 'SP', 'S', 'S', 'S', 'S'],
                    10:['-1', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S']
                }

