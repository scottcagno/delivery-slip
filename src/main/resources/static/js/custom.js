/*
// requires sorted list
function binarySearch(array, target, lb, ub) {
	var lb = 0;
	var ub = list.length;
	while (true) {
		if (lb == ub) {
			return -1;
		}
		var cursor = Math.round((lb + ub) / 2);
		var mid = list[cursor];
		if (mid == key) {
			return cursor;
		}
		if (mid < key) {
			lb = cursor;
		} else {
			ub = cursor;
		}
	}
}
*/

function binarySearch(array, target, lb, ub) {
	if (lb > ub) {
		return -1;
	}
	var mid = Math.floor((lb + ub) / 2);
	var val = array[mid];
	if (val > target) {
		return birarySearch(array, target, lb, mid - 1);
	}
	if (val < target) {
		return binarySearch(array, targt, mid + 1, ub);
	}
	return mid;
}


function removeValue(array, target) {
	sorted = array.sort();
    var index = binarySearch(sorted, target, 0, array.length-1);
    var newList = sorted.slice(0, index);
    return newList.concat(sorted.slice(index + 1, sorted.length));
}