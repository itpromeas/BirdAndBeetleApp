package org.meas;

import org.meas.Square.BeetlesSquareJump;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        int sizeOfAnEdge = 15;
        int birdTotalApproach_plus_1 = 101;
        BeetlesSquareJump beetlesSquareJump = new BeetlesSquareJump();

        try {
            beetlesSquareJump.beetleBoard(sizeOfAnEdge,birdTotalApproach_plus_1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}