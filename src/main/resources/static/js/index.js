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
    .then((response) => response.json())
    .then((datas) => {
      if (datas) {
        datas.forEach((data) => {
          console.log(data);
          resultParagraph.innerText += JSON.stringify(data) + "\n";
        });
      }
    })
    .catch((err) => {
      window.alert(err);
    });
});
