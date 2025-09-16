import sys
sys.stdin = open('1759.txt', 'r')

L, C = map(int, input().split())
arr = input().split()
arr.sort()  # 알파벳 순서대로 출력하기 위해 sort를 진행
lst = ""    # 출력을 위한 빈 문자열
vowel = ['a', 'e', 'i', 'o', 'u']   # 모음 list
v_cnt = 0   # 모음 개수 체크위한 변수
c_cnt = 0   # 자음 개수 체크위한 변수

def password(lst, s):
    global v_cnt, c_cnt
    if len(lst) == L:   # lst 문자열의 길이가 L과 같아질 때
        if c_cnt >= 2 and v_cnt >= 1:   # 모음이 1개 이상, 자음이 2개 이상 포함 되어 있을 경우
            print(lst)  # 출력
        return

    for i in range(s, C):

        if arr[i] in vowel:     # 반복문으로 가져온 i가 모음일 때 모음 갯수를 세면서 재귀 진행
            v_cnt += 1
            password(lst+arr[i], i+1)
            v_cnt -= 1

        else:                   # 반복문으로 가져온 i가 자음일 때 자음 갯수를 세면서 재귀 진행
            c_cnt += 1
            password(lst+arr[i], i+1)
            c_cnt -= 1

password(lst, 0)