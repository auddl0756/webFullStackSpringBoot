document.addEventListener("DOMContentLoaded", initReservePage);

async function initReservePage() {
    const displayInfoId = $('#container').attr('data-displayInfoId');

    let titleArea = new TitleArea();
    let titleData = await titleArea.requestDisplayInfo(displayInfoId);
    console.log(titleData);

    titleArea.drawTitleImage(titleData);
    titleArea.drawTitleDetail(titleData);

    let ticket = new Ticket();
    ticket.drawTicketArea(titleData.priceInfos, titleData.displayInfo.productId);
    ticket.addEventListeners();

    let bookingForm = new BookingForm(titleData.displayInfo, titleData.priceInfos);

}

class TitleArea {
    constructor() {
        this.titleImageSection = $('.visual_img');
        this.titleImageTemplate = $('#titleImageTemplate').html();

        this.titleDetailSection = $('.section_store_details');
        this.titleDetailTemplate = $('#titleDetailTemplate').html();

    }

    requestDisplayInfo(displayInfoId) {
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: "/reserve/displayInfo/" + displayInfoId,
                type: "get",
                success: function (response) {
                    resolve(response);
                },
                error: function () {
                    alert("detail comment data load failed.");
                }
            });
        });
    }

    drawTitleImage(titleData) {
        let bindTemplate = Handlebars.compile(this.titleImageTemplate);
        let resultHTML = "";
        let minPrice = 1000_000_000;
        for (const price of titleData.priceInfos) {
            minPrice = Math.min(minPrice, price.discountedPrice);
        }

        titleData.displayInfo.minPrice = minPrice;

        resultHTML = bindTemplate(titleData.displayInfo);

        this.titleImageSection.html(resultHTML);
    }

    drawTitleDetail(titleData) {
        let bindTemplate = Handlebars.compile(this.titleDetailTemplate);
        let processedInfo = this.preprocessDisplayInfo(titleData);
        let resultHTML = bindTemplate(processedInfo);

        this.titleDetailSection.html(resultHTML);
    }

    preprocessDisplayInfo(titleData) {
        let resultInfo = {};
        for (let key in titleData.displayInfo) {
            resultInfo[key] = titleData.displayInfo[key];
        }
        resultInfo['priceInfos'] = titleData.priceInfos;

        return resultInfo;
    }
}

class Ticket {
    constructor() {
        this.ticketSection = $('.ticket_body');
        this.ticketTemplate = $('#ticketTemplate').html();
        this.ticketButton = $('.clearfix');

    }

    addEventListeners() {
        $('.clearfix').on("click", this.ticketSelectEvent.bind(this));
    }

    drawTicketArea(priceInfos, productId) {
        let bindTemplate = Handlebars.compile(this.ticketTemplate);
        let resultHTML = "";

        for (let priceInfo of priceInfos) {
            resultHTML += bindTemplate(priceInfo);
        }

        this.ticketSection.html(resultHTML);
    }

    ticketSelectEvent() {
        let clickedTag = event.target;
        let id = clickedTag.id;
        let parentTag = clickedTag.closest('.clearfix');
        let countTag = $(parentTag).find('.count_control_input');
        let value = parseInt($(countTag).val());
        let minusButton = $(parentTag).find('#minus');

        if (id === 'plus') {
            value += 1;
        } else if (id === 'minus') {
            value = Math.max(0, value - 1);
        }

        $(countTag).val(value);

        this.changeTicketCSS(parentTag);

    }

    changeTicketCSS(parentTag) {
        let countTag = $(parentTag).find('.count_control_input');
        let minusButton = $(parentTag).find('#minus');
        let value = parseInt($(countTag).val());

        let priceInfoSection = $(parentTag).parent().next();
        const unitPrice = parseInt($(priceInfoSection).find('.price').html());

        let totalPriceSection = $(parentTag).next();
        let totalPriceTag = $(totalPriceSection).find('.total_price');
        const totalPrice = unitPrice * value;
        totalPriceTag.html(totalPrice);

        if (value === 0) {
            minusButton.addClass('disabled');
            $(countTag).addClass('disabled');
            $(totalPriceSection).removeClass('on_color');
        } else {
            minusButton.removeClass('disabled');
            $(countTag).removeClass('disabled');
            $(totalPriceSection).addClass('on_color');
        }
    }
}


class BookingForm {
    constructor(displayInfo, priceInfos) {
        this.form = $('.form_horizontal');
        this.formName = $(this.form).find('#name');
        this.formTel = $(this.form).find('#tel');
        this.formEmail = $(this.form).find('#email');
        this.displayInfoId = displayInfo.displayInfoId;
        this.priceInfos = priceInfos;
        this.productId = displayInfo.productId;
        this.reservationDate = displayInfo.reservationDate;

        this.setReservationDate(displayInfo.reservationDate);
        this.bookButton = $('.bk_btn');

        this.addEventListeners();
    }

    setReservationDate(reservationDate) {
        $('#reservation_description').prepend(reservationDate + ", 총");
    }

    addEventListeners() {
        this.bookButton.on('click', this.submitFormEvent.bind(this));
    }

    submitFormEvent() {
        let bookingData = {};
        bookingData['name'] = $(this.formName).val();
        bookingData['tel'] = $(this.formTel).val();
        bookingData['email'] = $(this.formEmail).val();
        bookingData['displayInfoId'] = this.displayInfoId;
        bookingData['productId'] = this.productId;
        bookingData['reservationDate'] = this.reservationDate;
        let ticketButtons = $('.clearfix');

        let prices = [];

        for (let ticketButton of ticketButtons) {
            //선택하지 않은 티켓은 저장 요청하지 않도록 하기 위해
            let count = $(ticketButton).find('.count_control_input').val();
            if (count <= 0) {
                continue;
            }

            let price = {};
            price['count'] = count;
            price['productPriceId'] = $(ticketButton).find('.productPriceIdHidden').val();
            prices.push(price);
        }
        bookingData['prices'] = prices;

        $.ajax({
            url: "/api/reservations",
            type: "POST",
            data: JSON.stringify(bookingData),     //JSON.stringfy(객체) : 객체를 json 문자열로 변환.
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function () {
                location.href = "/";
            },
            error: function () {
                console.log("failed to make a reserve.");
            }
        });
    }
}

