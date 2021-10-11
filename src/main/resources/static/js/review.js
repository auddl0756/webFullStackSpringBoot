document.addEventListener("DOMContentLoaded", initReviewPage);

// reivew.js에선 따로 클래스 안 둬도 되겠다. 단순히 모든 댓글을 가져와서 보여주면 되니까

async function initReviewPage() {
    const displayInfoId = $('#container').attr('data-displayInfoId');

    let commentData = await requestAllComments(displayInfoId);

    drawComments(commentData.comments);
    drawGrade(parseFloat(commentData.averageScore), commentData.comments.length);
}

function requestAllComments(displayInfoId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            url: "/api/comments/" + displayInfoId,
            type: "get",
            success: function (response) {
                resolve(response);
            },
            error: function () {
                alert("review comment data load failed.");
            }
        })
    });
}

function drawComments(commentData) {
    let bindTemplateWithNoImage = Handlebars.compile($('#commentItemWithNoImageTemplate').html());
    let bindTemplateWithImage = Handlebars.compile($('#commentItemWithImageTemplate').html());

    let resultHTML = "";

    for (let cData of commentData) {
        if (cData.image === null) {
            resultHTML += bindTemplateWithNoImage(cData);
        } else {
            cData.saveFileName = cData.image.saveFileName;
            resultHTML += bindTemplateWithImage(cData);
        }
    }

    $('.list_short_review').html(resultHTML);
}

function drawGrade(averageScore, totalCount) {
    $('.graph_value').css('width', 100 * averageScore / 5.0 + "%");
    $('.text_value > span').html(averageScore.toFixed(1));
    $('.join_count > .green').html(totalCount + "건");
}
