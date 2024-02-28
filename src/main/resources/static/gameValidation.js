function validateNumber(input) {
    const value = parseInt(input.value, 10);
    const invalidFeedback = input.nextElementSibling;

    if (isNaN(value) || value < 0 || value > 10000) {
        input.setCustomValidity('invalid');
        invalidFeedback.style.display = 'block';
    } else {
        input.setCustomValidity('');
        invalidFeedback.style.display = 'none';
    }
}