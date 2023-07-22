package org.meas.Square;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BeetlesSquareJumpTest{

    BeetlesSquareJump beetlesSquare;
    int n;
    int n_1;

    @BeforeEach
    void setUp() {
        beetlesSquare = new BeetlesSquareJump();
        n = 15;
        n_1 = n-1;
    }

    @Test
    void listOfAdjacentIndexesOfEdgesXiY_0ShouldBeEmpty(){
        for(int i=0;i<n;i++){
            assertEquals(0,beetlesSquare.getAdjacentIndexes(i,0, n, n).size());
        }
    }

    @Test
    void listOfAdjacentIndexesOfEdgesXiY_nShouldBeEmpty(){
        for(int i=0;i<n;i++){
            assertEquals(0,beetlesSquare.getAdjacentIndexes(i,n_1, n, n).size());
        }
    }

    @Test
    void listOfAdjacentIndexesOfEdgesX_0Y_iShouldBeEmpty(){
        for(int i=0;i<n;i++){
            assertEquals(0, beetlesSquare.getAdjacentIndexes(0, i, n,n).size());
        }
    }

    @Test
    void listOfAdjacentIndexesOfEdgesX_nY_iShouldBeEmpty(){

        for(int i=0;i<n;i++){
            assertEquals(0, beetlesSquare.getAdjacentIndexes(n_1, i, n,n).size());
        }
    }

    @Test
    void adjacentIndexesListSizeShouldBeEqualTo8(){
        for(int i=1;i < n_1;i++) {
            for (int j = 1; j < n_1; j++) {
                assertEquals(8, beetlesSquare.getAdjacentIndexes(i, j, n, n).size());
            }
        }
    }


    @Test
    void eachSquareMustBeEqualToOneAtTheInitialization(){
        int[][] beetleSquareMatrix = new int[n][n];
        Arrays.stream(beetleSquareMatrix).forEach(r -> Arrays.fill(r,1));

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                assertTrue(beetleSquareMatrix[row][col] == 1 );
            }
        }
    }

}