package main

import "C"
import "fmt"

//export Add
func Add(a, b int) int {
	return a + b
}

// 一個天線（桶）上的所有球號的信息，_NUMBEROFBARRELBALL_是一個天線上可開的球號的最大數目
type tt struct {
	age  int
	name string
}

func changTT(t *tt) {
	t.age = 100

}

/**
* 快忘了*的二個用法，１->指標 , 2->取出地址的值
* var v *int -> v是指標變數
*
*
 */
func main() {
	var a = Add(1, 3)
	fmt.Println(a)

	// new 是屬於指標
	var t = new(tt)
	t.age = 10
	t.name = "abc"
	//t.a{age:10,name: "test"}
	changTT(t)
	fmt.Println(t.age)

	//
	var i int = 10
	var ai *int
	ai = &i
	fmt.Println(*ai)

	// 直接使用物件非指標變數
	t1 := tt{10, "sammy"}
	changTT(&t1)
	fmt.Println(t.age)
}
