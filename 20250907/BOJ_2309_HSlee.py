# BOJ_2309 : 일곱난쟁이
# https://www.acmicpc.net/problem/2309

height = [int(input()) for _ in range(9)]
x, y = 0, 0
S = sum(height) # 전체 키를 더한 값
result = []

for i in range(9):
    for j in range(i+1, 9):
        if S - (height[i]+height[j]) == 100:
        # 전체 키를 더한 값에서 중복되지않는 두 수를 더한 값을 뺐을때 100이 나오는 경우
            x, y = height[i], height[j]
            break
    if x != 0:
        break # x와 y값이 0이 아니면(입력이 들어갔으면) break를 해줘서 시간을 줄임

for k in height:
    if k != x and k != y: # height값 중에 x와 y가 아닌걸 result에 append함
        result.append(k)

result.sort() # 오름차순으로 정렬

for row in result:
    print(row)