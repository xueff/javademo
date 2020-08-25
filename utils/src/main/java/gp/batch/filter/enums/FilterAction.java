package gp.batch.filter.enums;

public enum FilterAction {
    Equals,
    Large,
    ELarge,
    Less,
    ELess,
    Between,
    In,   //eq
    AllIn,   //eq
    Contains,    // eq 和 like
    Prefix, Suffix, Regex;
}
