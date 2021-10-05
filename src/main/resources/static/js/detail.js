document.addEventListener("DOMContentLoaded", initDetailPage);

async function initDetailPage() {
    // const displayInfoId = location.search.split("=")[1].replace("#","");

    const displayInfoId = $('#container').attr('data-displayInfoId');

    let titleArea = new TitleArea();
    let titleData = await titleArea.requestTitleData(displayInfoId);
    console.log(titleData);

    titleArea.drawTitleImage(titleData);

}

class TitleArea {
    constructor(displayInfoId) {
        this.template = $('#detailTitleTemplate').html();

    }

    requestTitleData(displayInfoId) {
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: "/api/detailData/" + displayInfoId,
                type: "get",
                success: function (response) {
                    resolve(response);
                },
                error: function () {
                    alert("promotion data load failed.");
                }
            });
        });
    }

    drawTitleImage(titleData) {
        let bindTemplate = Handlebars.compile(this.template);
        let resultHTML = "";
        for (let tData of titleData) {
            resultHTML += bindTemplate(tData);
        }

        resultHTML+=bindTemplate(titleData[0]);

        $('.visual_img').html(resultHTML);
    }
}