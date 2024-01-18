# Checkout items with rules exercise
Implementation inspired by http://codekata.com/kata/kata09-back-to-the-checkout/ task.

It is very simplified implementation without datasource. Data are actually loaded only for Unit Tests into class ``Goods`` which is used as a datasource mock.

##
Key points:
* Items in cart are stored only under SKU and quantity - Item price is late bound during calculating total. Can be changed during ``Checkout.scan()`` method.
* Currently only single rule is possible per SKU - To enable using multiple rules (like percentage based on amount), their priority and exclusions has to be resolved.
* ``BigDecimal`` is used as it should be used in terms of currency, but can be replaced by ``double`` which makes implementation much more easier.
* ``DefaultPriceRule`` is default pricing for all SKUs without any special pricing rule.
* Every new type of rule, can easily implement ``CheckoutRule`` and implement custom pricing calculation in ``applyRule()`` method.
