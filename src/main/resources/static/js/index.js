document.getElementById("submitForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const paramValue = document.getElementById("apiParam").value;
  const resultParagraph = document.getElementById("result");
  resultParagraph.innerText = "";
  const formData = new FormData();
  formData.append("param", paramValue);
  fetch("/api/attack", {
    method: "POST",
    body: formData,
  })
    .then((response) => {
      if (response.status === 200) {
        return response.json();
      } else if (response.status === 400) {
        throw new Error("잘못된 SQL 문법입니다.");
      } else {
        throw new Error("알 수 없는 에러입니다. 나중에 다시 시도하세요.");
      }
    })
    .then((datas) => {
      if (datas) {
        datas.forEach((data) => {
          resultParagraph.innerText += JSON.stringify(data) + "\n";
        });
      } else {
        resultParagraph.innerText += "데이터가 존재하지 않습니다.";
      }
    })
    .catch((err) => {
      window.alert(err.message);
    });
});
