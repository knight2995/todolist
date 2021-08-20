function checkDelete(){
    if (confirm("정말 삭제하시겠습니까??") == true){    //확인
        return true;
    }else{   //취소
        return false;
    }
}

function checkTitleLength() {

    let title = document.getElementById("title").value
    //오로지 공백인지 체크

    let trimTitle = title.trim();

    if(trimTitle.length === 0){
        alert("공백 타이틀은 입력할 수 없습니다.");
        return false;
    }
    if(title.length > 20){
        alert("타이틀의 길이는 20을 넘을 수 없습니다.");
        return false;
    }

    return true;
}