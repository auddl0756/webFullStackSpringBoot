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
        this.ticketButtons = $('.clearfix');
        this.totalTicketCountArea = $('#totalCount');
        this.totalTicketCount = 0;
    }

    addEventListeners() {
        // UI가 그려지고 나서야 select 가능해서.. this.ticketButtons를 못 씀.
        $('.clearfix').on("click", this.ticketSelectEvent.bind(this));
        $('.clearfix').on('click', this.setTotalTicketCount.bind(this));
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

    setTotalTicketCount() {
        this.totalTicketCount = 0;
        let ticketButtons = $('.clearfix');
        for (let ticketButton of ticketButtons) {
            this.totalTicketCount += parseInt($(ticketButton).find('.count_control_input').val());
        }

        $(this.totalTicketCountArea).html(this.totalTicketCount);
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
        this.reservationButton = $('.bk_btn');
        this.agreeButtons = $('.btn_agreement');
        this.allAgreeButton = $('#chk3');
        $('#chk3').val('off');

        this.addEventListeners();
    }

    setReservationDate(reservationDate) {
        $('#reservation_description').prepend(reservationDate + ", 총");

    }

    addEventListeners() {
        this.reservationButton.on('click', this.validateForm.bind(this));

        this.formName.on('change', this.errorName.bind(this));
        this.formTel.on('change', this.errorPhone.bind(this));
        this.formEmail.on('change', this.errorEmail.bind(this));

        this.agreeButtons.on('click', this.agreeTextShowEvent.bind(this));

        this.allAgreeButton.on('click',this.changeStateOfAgreeButton.bind(this));
    }

    changeStateOfAgreeButton(){
        let buttonValue = this.allAgreeButton.val();
        if(buttonValue === 'on'){
            $(this.allAgreeButton).val('off');
        }else{
            $(this.allAgreeButton).val('on');
        }
    }

    validateName() {
        let regExprEnglishName = new RegExp(/^[A-Za-z]+$/);
        let formInputName = $('#name').val();

        let regExprKoreanName = new RegExp(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/);

        return regExprEnglishName.test(formInputName) || regExprKoreanName.test(formInputName);
    }

    errorName() {
        let isNameValid = this.validateName();
        if (isNameValid === false) {
            $('#name_warning').css('visibility', 'visible');

            setTimeout(function () {
                $('#name_warning').css("visibility", "hidden")
            }, 1000);
        }
    }

    validatePhone() {
        let regExprPhone = new RegExp(/\d{3}[-]\d{4}[-]\d{4}/);
        let formInputPhone = $('#tel').val();

        return regExprPhone.test(formInputPhone);
    }

    errorPhone() {
        let isPhoneValid = this.validatePhone();
        if (isPhoneValid === false) {
            $('#tel_warning').css('visibility', 'visible');

            setTimeout(function () {
                $('#tel_warning').css("visibility", "hidden")
            }, 1000);
        }
    }

    validateEmail() {
        let regExprEmail = new RegExp(/^[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+$/);
        let formInputEmail = $('#email').val();

        return regExprEmail.test(formInputEmail);
    }

    errorEmail() {
        let isEmailValid = this.validateEmail();
        if (isEmailValid === false) {
            $('#email_warning').css('visibility', 'visible');

            setTimeout(function () {
                $('#email_warning').css("visibility", "hidden")
            }, 1000);
        }
    }

    validateTicket() {
        const totalTicketCount = parseInt($('#totalCount').html());
        return totalTicketCount !== 0;
    }

    validateAgreeButton() {
        console.log($(this.allAgreeButton).val());

        if ($(this.allAgreeButton).val() === 'on') {
            return true;
        } else {
            return false;
        }
    }

    validateForm() {
        let isFormValid = this.validateName() && this.validatePhone() && this.validateEmail();
        let isAnyTicketSelected = this.validateTicket();
        let isCheckedPolicy = this.validateAgreeButton();

        let totalValid = isFormValid && isAnyTicketSelected && isCheckedPolicy;
        if (totalValid === true) {
            this.submitFormEvent();
        } else {
            if (isFormValid === false) {
                alert("올바른 정보를 입력해주세요.");
            } else if (isAnyTicketSelected === false) {
                alert("하나 이상의 티켓을 선택해주세요.");
            } else if (isCheckedPolicy === false) {
                alert("약관 동의에 체크해 주세요.");
            }
        }
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
            success: function (httpStatus) {
                if(httpStatus === 'CREATED'){
                    location.href="/";
                }else if(httpStatus === 'BAD_REQUEST'){
                    alert("bad request. failed to make a reservation");
                }
            }
        });
    }

    agreeTextShowEvent() {
        let agreeTag = event.target.closest('.agreement');
        let arrowButton = $(agreeTag).find('.fn');

        if (agreeTag.classList.contains('open')) {    //close
            $(agreeTag).removeClass('open');
            $(arrowButton).addClass('fn-down2');
            $(arrowButton).removeClass('fn-up2');
        } else {  //open
            $(agreeTag).addClass('open');
            $(arrowButton).removeClass('fn-down2');
            $(arrowButton).addClass('fn-up2');
        }
    }
}

