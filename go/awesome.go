package main

import "C"
import "fmt"

//export Add
func Add(a, b int) int {
	return a + b
}

func main() {
	var a = Add(1, 3)
	fmt.Println(a)

}
