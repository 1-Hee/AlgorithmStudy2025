height = [int(input()) for _ in range(9)]
x, y = 0, 0
S = sum(height)
result = []

for i in range(9):
    for j in range(i+1, 9):
        if S - (height[i]+height[j]) == 100:
            x, y = height[i], height[j]
            break
    if x != 0:
        break

for k in height:
    if k != x and k != y:
        result.append(k)

result.sort()

for row in result:
    print(row)