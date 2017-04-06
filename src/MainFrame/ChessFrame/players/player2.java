

    public boolean Check_The_Way_to_Postion(player1 enemy, Point newP) {
        boolean flag = false;

        for (int i = 1; i <= 32; i++) {
            if (inHand != i)// check if there is peices in the WAY
            {
                if (i < 17)
                    flag = checktheWay(newP, enemy.returnPostion(i), inHand);//Means there is somting in the Way so can't move
                else {
                    flag = checktheWay(newP, returnPostion(i), inHand);
                }
                if (flag == true) {
                    return false;
                }//Means  there is a Pice in the Way
            }
        }
        return true;

    }