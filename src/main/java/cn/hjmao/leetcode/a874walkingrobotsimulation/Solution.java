package cn.hjmao.leetcode.a874walkingrobotsimulation;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hjmao.
 *
 * URL:
 * =====
 * https://leetcode.com/problems/walking-robot-simulation/
 *
 * Desc:
 * =====
 * A robot on an infinite grid starts at point (0, 0) and faces north.
 * The robot can receive one of three possible types of commands:
 *   -2: turn left 90 degrees
 *   -1: turn right 90 degrees
 *   1 <= x <= 9: move forward x units
 * Some of the grid squares are obstacles.
 *
 * The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])
 *
 * If the robot would try to move onto them,
 * the robot stays on the previous grid square instead
 * (but still continues following the rest of the route.)
 *
 * Return the square of the maximum Euclidean distance
 * that the robot will be from the origin.
 *
 * Example 1:
 * Input: commands = [4,-1,3], obstacles = []
 * Output: 25
 * Explanation: robot will go to (3, 4)
 *
 * Example 2:
 * Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * Output: 65
 * Explanation: robot will be stuck at (1, 4)
 * before turning left and going to (1, 8)
 *
 * Note:
 * 0 <= commands.length <= 10000
 * 0 <= obstacles.length <= 10000
 * -30000 <= obstacle[i][0] <= 30000
 * -30000 <= obstacle[i][1] <= 30000
 * The answer is guaranteed to be less than 2 ^ 31.
 */

public class Solution {
  public int robotSim(int[] commands, int[][] obstacles) {
    Set<String> set = new HashSet<>();
    for (int[] obs : obstacles) {
      set.add(obs[0] + " " + obs[1]);
    }

    int[] pos = {0, 0};
    int direction = 1;
    int axis = 1;
    int max = 0;
    for (int command: commands) {
      switch (command) {
        case -2:
          direction = axis == 1 ? -direction : direction;
          axis = axis ^ 1;
          break;
        case -1:
          direction = axis == 0 ? -direction : direction;
          axis = axis ^ 1;
          break;
        default:
          int i = 1;
          for (; i <= command; i++) {
            String temp = axis == 0 ? (pos[0] + i * direction) + " " + pos[1]
                                    : pos[0] + " " + (pos[1] + i * direction);
            if (set.contains(temp)) {
              break;
            }
          }
          pos[axis] = pos[axis] + (i - 1) * direction;
          max = Math.max(max, pos[0] * pos[0] + pos[1] * pos[1]);
          break;
      }
    }
    return max;
  }
}
