# BOJ 10817. 세 수
# URL : https://www.acmicpc.net/problem/10817

A, B, C = map(int,input(). split()) # " "으로 구분되는 세 수 A, B, C를 정수로 입력받는다

lst = [A, B, C] # 입력받은 세 수를 lst라는 변수에 리스트로 할당
lst.sort() # sort 메서드를 이용해 리스트를 오름차순으로 정렬

print(lst[1]) # lst 변수에 할당된 리스트의 index 1번 자리에 있는 값 호출
