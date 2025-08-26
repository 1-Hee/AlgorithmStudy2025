# BOJ_1713 : 후보 추천하기
# https://www.acmicpc.net/problem/1713

N = int(input()) # 사진틀의 개수
rec = int(input()) # 추천 횟수
rec_list = list(map(int, input().split())) # 목록
pic = {}
rec_number = 0 # 추천 들어온 순서(시간)

for i in rec_list:
    rec_number += 1

    if i in pic:        # pic에 있는지 없는지 판단, 있으면 추천수만 올리고 continue
        pic[i][0] += 1
        continue

    if len(pic) < N:    # pic이 비어있는 상태(덜찬 상태)면 그냥 올림
        pic[i] = [1, rec_number]

    else:               # pic이 가득 찬 상태면
        remove = min(pic.items(), key=lambda j: (j[1][0], j[1][1]))[0]
        del pic[remove] # 추천수가 제일 적은걸, 동률이면 순서가 오래된걸 제거
        pic[i] = [1, rec_number] # 새 학생 올리기

ans = sorted(pic.keys()) # 학생 번호를 오름차순으로 정렬렬
print(ans)