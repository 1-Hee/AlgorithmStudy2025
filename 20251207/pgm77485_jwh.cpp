#include <string>
#include <vector>

using namespace std;

vector<vector<int>> map;


const int dx[4] = { 0,1,0,-1 };
const int dy[4] = { 1,0,-1,0 };


int rotate(int r1, int c1, int r2, int c2)
{
    int d = 0;
    int x = r1;
    int y = c1;
    int prev = map[r1][c1];
    int minValue = prev;

    while (true) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (nx < r1 || ny < c1 || nx > r2 || ny > c2) {
            d = (d + 1) % 4;
            continue;
        }

        int cur = map[nx][ny];
        map[nx][ny] = prev;
        prev = cur;

        minValue = prev < minValue ? prev : minValue;

        x = nx;
        y = ny;
        if (x == r1 && y == c1) break;
    }


    return minValue;
}

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {

    map.clear();

    int idx = 1;
    for (int i = 0; i < rows; i++)
    {
        vector<int> line;
        for (int j = 0; j < columns; j++)
        {
            line.push_back(idx++);
        }
        map.push_back(line);
    }

    vector<int> answer;

    for (int i = 0; i < queries.size(); i++)
    {
        int r1 = queries[i][0] - 1;
        int c1 = queries[i][1] - 1;
        int r2 = queries[i][2] - 1;
        int c2 = queries[i][3] - 1;
        int value = rotate(r1, c1, r2, c2);
        answer.push_back(value);
    }

    return answer;
}